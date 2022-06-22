package com.dawei.boot.boothelloword.utils.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author sinbad on 2020/4/26.
 */
public class JolTestUtil {


	public static void main(String[] args) {
		Object object = new Object();
		System.out.println(ClassLayout.parseInstance(object).toPrintable());

		System.out.println("##############");

		synchronized (object) {
			System.out.println(ClassLayout.parseInstance(object).toPrintable());
		}

	}
}
