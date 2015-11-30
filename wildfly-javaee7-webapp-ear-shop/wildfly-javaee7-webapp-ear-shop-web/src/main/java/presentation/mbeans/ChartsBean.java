package presentation.mbeans;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;

import services.TransactionServiceLocal;

@ManagedBean
@RequestScoped
public class ChartsBean {

	@EJB
	private TransactionServiceLocal transactionServiceLocal;

	private PieChartModel pieModel;

	public ChartsBean() {
	}

	@PostConstruct
	public void init() {
		Map<String, Long> sales = transactionServiceLocal.sales();
		pieModel = new PieChartModel();
		for (Entry<String, Long> entry : sales.entrySet()) {
			pieModel.set(entry.getKey(), entry.getValue());
		}
		pieModel.setTitle("Sales");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);

	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

}
