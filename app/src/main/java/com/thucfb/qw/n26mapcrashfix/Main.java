package com.thucfb.qw.n26mapcrashfix;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

@SuppressWarnings("unused")
public class Main implements IXposedHookLoadPackage {
	@Override
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("de.number26.android"))
			return;
		findAndHookMethod("de.number26.machete.android.ui.map.MapFragment", lpparam.classLoader, "Db", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(MethodHookParam param) {
				if (param.getThrowable() != null)
					param.setResult(null);
			}
		});
	}
}
