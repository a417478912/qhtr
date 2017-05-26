package com.qhtr.common.comet;

public class Comet {
    private String storeId;
    private String msgCount;
    private String msgData;
    public String getMsgCount() {
        return msgCount;
    }
    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMsgData() {
		return msgData;
	}
	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}
}