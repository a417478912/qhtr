package com.qhtr.log;

import java.lang.annotation.*;

/**
 * 
 * @author wjx
 *
 * @date  2017年3月29日
 * 
 *  @description 类的方法描述注解 
 */

@Target(ElementType.METHOD)     
@Retention(RetentionPolicy.RUNTIME)     
@Documented    
@Inherited  
public @interface MyLogAnnotation {
	public String description() default "no description";
}
