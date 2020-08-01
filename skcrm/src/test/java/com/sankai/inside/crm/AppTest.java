package com.sankai.inside.crm;

import com.sankai.inside.crm.core.utils.IdUtils;

public class AppTest {

	public static void main(String[] args) {

	    Outer outer = new Outer();
        Outer.Inner in = outer.new Inner();
        System.out.println(in);


        Outer.Inner iin = outer.getInnerInit();
        System.out.println(iin);

    }

}
