package cn.itcast.entity.condition;

public class LengthRange {
	private Integer from;
	private Integer to;
	// 加上这个属性，只是为了前端方便显示
	private String tip;
	
	public LengthRange() {
		super();
	}
	
	public LengthRange(Integer from, Integer to) {
		super();
		this.from = from;
		this.to = to;
	}


	public LengthRange(Integer from, Integer to, String tip) {
		super();
		this.from = from;
		this.to = to;
		this.tip = tip;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "LengthRange [from=" + from + ", to=" + to + ", tip=" + tip + "]";
	}
}
