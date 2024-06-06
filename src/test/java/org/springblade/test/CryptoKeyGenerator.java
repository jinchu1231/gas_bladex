package org.springblade.test;

import org.springblade.core.tool.utils.AesUtil;
import org.springblade.core.tool.utils.DesUtil;
import org.springblade.core.tool.utils.StringPool;

/**
 * Key生成器
 *
 * @author Chill
 */
public class CryptoKeyGenerator {

	public static void main(String[] args) {
		System.out.println("=======================================================");
		System.out.println("====== blade.token.crypto-key 的值从中挑选一个便可 =======");
		System.out.println("=======================================================");
		for (int i = 0; i < 10; i++) {
			String cryptoKey = AesUtil.genAesKey();
			System.out.println("BladeX CryptoKey：[" + cryptoKey + "] ");
		}
		System.out.println("=======================================================");
		System.out.println(StringPool.EMPTY);
		System.out.println("=======================================================");
		System.out.println("===== blade.api.crypto.aes-key 的值从中挑选一个便可 ======");
		System.out.println("=======================================================");
		for (int i = 0; i < 10; i++) {
			String AesKey = AesUtil.genAesKey();
			System.out.println("BladeX AesKey：[" + AesKey + "] ");
		}
		System.out.println("=======================================================");
		System.out.println(StringPool.EMPTY);
		System.out.println("=======================================================");
		System.out.println("===== blade.api.crypto.des-key 的值从中挑选一个便可 ======");
		System.out.println("=======================================================");
		for (int i = 0; i < 10; i++) {
			String DesKey = DesUtil.genDesKey();
			System.out.println("BladeX DesKey：[" + DesKey + "] ");
		}
		System.out.println("=======================================================");
	}

}
