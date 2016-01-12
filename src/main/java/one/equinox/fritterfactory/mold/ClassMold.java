package one.equinox.fritterfactory.mold;


import one.equinox.fritterfactory.ReflectionUtil;

public class ClassMold extends InstanceMold{
    public ClassMold(Class moldInstance) {
        super(ReflectionUtil.newInstance(moldInstance));
    }
}
