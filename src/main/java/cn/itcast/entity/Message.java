package cn.itcast.entity;

import java.util.HashMap;

public class Message {
	private Integer status;
	private String msg;
	private HashMap<String, Object> data = new HashMap<>();

	public Message(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public Message() {
		super();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
}
