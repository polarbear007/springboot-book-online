package cn.itcast.entity.condition;

/**
 * 只是为了方便前台展示，才建的这个实体类
 * 
 * @author Administrator
 *
 */
public class IsFreeWrapper {
	private Integer isFree;
	private String tip;

	public IsFreeWrapper(Integer isFree, String tip) {
		super();
		this.isFree = isFree;
		this.tip = tip;
	}

	public IsFreeWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
}
