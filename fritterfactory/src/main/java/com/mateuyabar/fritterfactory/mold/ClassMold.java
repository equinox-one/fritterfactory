package com.mateuyabar.fritterfactory.mold;


import com.mateuyabar.fritterfactory.ReflectionUtil;

public class ClassMold extends InstanceMold{
    public ClassMold(Class moldInstance) {
        super(ReflectionUtil.newInstance(moldInstance));
    }
}
