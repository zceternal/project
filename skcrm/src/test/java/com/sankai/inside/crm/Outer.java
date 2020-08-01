package com.sankai.inside.crm;

import java.util.Objects;

public class Outer {

    private Inner inner;

    public Inner getInnerInit(){
        if (Objects.isNull(inner)) {
            inner = new Inner();
        }
        return inner;
    }

    class Inner{

    }

}
