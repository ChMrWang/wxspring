package com.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;

/**
 * @author Mr Wang
 * 被动文本回复匹配值
 */
@Entity
public class TextAns {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mapId;
	private String receive;
	private String response;
	public String getReceive() {
		return receive;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	
	}
}
