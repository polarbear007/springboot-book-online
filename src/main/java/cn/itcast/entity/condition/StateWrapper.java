package cn.itcast.entity.condition;
/**
 * 因为我们在数据库里面直接约定图书的两种状态，1表示连载中，2表示已经完结
 * 为了方便前台展示数据，我们这里封装一个 StateWrapper
 * @author Administrator
 *
 */
public class StateWrapper {
	private Integer state;
	private String tip;
	
	public StateWrapper(Integer state, String tip) {
		super();
		this.state = state;
		this.tip = tip;
	}
	public StateWrapper() {
		super();
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
}
