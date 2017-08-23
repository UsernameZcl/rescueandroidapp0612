package com.rescueandroid.exception;

/**
 * 网络连接异常
 *
 * @author zhubin
 * @date 2013-08-16
 */
public class NetConnectionException extends Exception {
	public NetConnectionException() {
	}

	public NetConnectionException(Exception e) {
		super(e);
	}
}