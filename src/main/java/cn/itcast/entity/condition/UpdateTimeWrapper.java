package cn.itcast.entity.condition;

/**
 * 同样是为了方便前台显示，才创建这个实体类
 * 
 * @author Administrator
 *
 */
public class UpdateTimeWrapper {
	private Integer daysAgo;
	private String tip;

	public UpdateTimeWrapper(Integer daysAgo, String tip) {
		super();
		this.daysAgo = daysAgo;
		this.tip = tip;
	}

	public UpdateTimeWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getDaysAgo() {
		return daysAgo;
	}

	public void setDaysAgo(Integer daysAgo) {
		this.daysAgo = daysAgo;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}