package com.qhtr.utils.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：1.0
 *日期：2016-06-06
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	//合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String partner = "2088521548721245";

	//商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
	public static String private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCEtFyj5VCJ/pMi5HieNJvLUTXX6h9oHfumckhW8N8smwjMDdbzPDudBOyjzh/L9gGItvb3/5hpkIiULf0JNqjO90KvULCLj02A0tyiFkek90p972LjcdjRHqAsswlkccMzPVFqdVPP1XJEdJgYbHXR75HTw0XbPOfF8CGZYn5fCVvzKVKklSHeEEVr5/zXty5uJv1qNFyAwRmdnO6W0gohu0MQqzLDgbxdxOOU+5ndh+7AjsMfbdF6dx3Xg7sxCFY2S6kCjcWrXtLQbst6rU0OTqREfDdFV0nq3aLAIT1BJkToc43CJ/f2QWngjzjVEP6StEQS9RcyeVAXDk7I75UHAgMBAAECggEAMi20/fA+5P8TCdWUWlYQNvANSQhpOchkmIqMGfvYl5hD2ky9Y5n5/KSZClNtqITsDZMJjbrjGb+iNU2yaWJ2sFPErl/3cb4NE1IoRU3hZ1pum3ICVXh+AIzxChXkzktKAT0FZRjaSTMvkvz3afzRDEHnRUiISJ8eELV9Lu+UU0C6jUSq4K591AMgTG097HnnJFOtngCTXsh6nTCBbeds87zdVF76VsH2EHn+fV62tyd16cqL1hSri8nMddbeb5pRYaNShlQ5GkW/0RnBEYHcx8bxcYPtpmZR+0liBHEm5ytA2MWLH6x5+y+Zpynn9x9Jxb7KV0eOKDh197PsComCcQKBgQDoiXuHYYFoVT+v71DZg2abO/Mm0AKkCRrPXvxDBMlVWYkdea6C61u/U55OaQYBZcArqqjFZPUCP+7Rpatw5s5sYA1I61Vp9EvbmYvJ6Ca5UXfANM0mwKhW0NYo0VfAD0paEnHNjLvNIVEP1BjMf1fQgrxxC7IYwNM18XFzAljLLwKBgQCSGCtERRPNV9afwoMNSXgyReTvPm0rMbQOdIVEb9dxCG7L5eNhUzEbG3F/8vloGhS2lUIh9hgj/FGQaSP/4oVbcRnQZamGPZwSMv8fvrsISj7/0yyeoavLfQB9X+d8OGrUSMOnwRBfxMvwPGFGe88stW+lmBinsNWHCfrTy7z9qQKBgB+3mOTHVOpRLmWl48Y3WQh1bd3uY13SbBi64PeXMIr2snAVx8rNpW78TcxuIMaSBVsvZOO/uHd9Fur7iN3WbXN0ynsWtkjEbJBgaiO5CYcfaJEL4quVfaCRzZQ1sZ1duIt+lo0t0/PPxom9KUDDYcvr+R4GNV2Q934VF59TQ587AoGBAISrlCcUQA5bNVmxr4dSb+ffly44V7VBFs2FvTxQAKrySIO7ycipXs/KFUFnalzCSosgcoUa0gNj8MIe6QVINWi8BgA9QjzZGDFixFKUx46YtitmH4ATO0q4wsgYXsRtC36qcZuW9QKzWaxZeNEkQBjOz4vm2oT2ToKqanEJ9R+5AoGBAKSdnMKI61AWxuNBeGd2MVVtoZ5XuwK+5VMIn4Cr6TgKYNhWWuXYr+vhKmhAzpzn8jIV+EP308wAW9o2RA0Gxq8PccTJd15/IepBg4R0Qp1n9ctUHWte3jt/OqiGk0iWfmkvGaQaBidQxuN77vz9oLPb+vxoGzg3thCgcLIieQur";
	
	//支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
	public static String alipay_public_key  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhLRco+VQif6TIuR4njSby1E11+ofaB37pnJIVvDfLJsIzA3W8zw7nQTso84fy/YBiLb29/+YaZCIlC39CTaozvdCr1Cwi49NgNLcohZHpPdKfe9i43HY0R6gLLMJZHHDMz1RanVTz9VyRHSYGGx10e+R08NF2zznxfAhmWJ+Xwlb8ylSpJUh3hBFa+f817cubib9ajRcgMEZnZzultIKIbtDEKsyw4G8XcTjlPuZ3YfuwI7DH23Rencd14O7MQhWNkupAo3Fq17S0G7Leq1NDk6kRHw3RVdJ6t2iwCE9QSZE6HONwif39kFp4I841RD+krREEvUXMnlQFw5OyO+VBwIDAQAB";
	
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path ="C://";
		
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 接收通知的接口名
	public static String service = "mobile.securitypay.pay";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
}

