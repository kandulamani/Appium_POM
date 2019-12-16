package com.Appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class BaseProgram {

	public void serverStart() {

		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.start();

	}

	public static void main(String[] args) {
		BaseProgram obj = new BaseProgram();
		obj.serverStart();

	}
}