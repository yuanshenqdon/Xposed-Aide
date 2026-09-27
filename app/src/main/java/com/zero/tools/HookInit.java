package com.zero.tools;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HookInit implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {

		if (BuildConfig.APPLICATION_ID.equals(lpparam.packageName)) {
			XposedHelpers.findAndHookMethod(
				MainActivity.class.getName(),
				lpparam.classLoader,
				"isModuleActivated",
				XC_MethodReplacement.returnConstant(true));
			return;
		}

		if ("com.cosmos.tools".equals(lpparam.packageName)) {
			try {
				XposedHelpers.findAndHookMethod(
					"com.cosmos.tools.entity.UserData",
					lpparam.classLoader,
					"isVip",
					new XC_MethodHook() {
						@Override
						protected void beforeHookedMethod(MethodHookParam param) {
							param.setResult(1);
						}
					});
			} catch (Throwable t) {
			}

			try {
				XposedHelpers.findAndHookMethod(
					"com.cosmos.tools.entity.FunctionData",
					lpparam.classLoader,
					"isVip",
					new XC_MethodHook() {
						@Override
						protected void beforeHookedMethod(MethodHookParam param) {
							param.setResult(true);
						}
					});
			} catch (Throwable t) {
			}
		}
	}
}