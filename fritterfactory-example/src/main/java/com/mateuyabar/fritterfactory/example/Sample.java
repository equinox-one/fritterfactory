//package com.mateuyabar.fritterfactory.example;
//
//import com.mateuyabar.fritterfactory.FritterFactory;
//import com.mateuyabar.fritterfactory.mold.InstanceMold;
//import com.mateuyabar.fritterfactory.mold.MapMold;
//import com.mateuyabar.fritterfactory.providers.ListItemProvider;
//import com.mateuyabar.fritterfactory.providers.ModelProvider;
//
//import java.util.List;
//
///**
// * Created by mateuyabar on 17/12/15.
// */
//public class Sample {
//
//    public  static void main(String[] args){
//        sampleUsingClassMolds();
//
//        sampleUsingMapMolds();
//    }
//
//    private static void sampleUsingMapMolds() {
//        FritterFactory factory = new FritterFactory();
//        List<Category> categories = factory.buildList(Category.class, 3);
//        factory.addProvider(Category.class, new ListItemProvider<Category>(categories));
//        MapMold adressMold = new MapMold();
//        factory.addProvider(Person.class, new ModelProvider<>(factory, Person.class, new InstanceMold(PersonMold.class)));
//        System.out.println(factory.buildList(Person.class));
//    }
//
//    private static void sampleUsingClassMolds() {
//        FritterFactory factory = new FritterFactory();
//        List<Category> categories = factory.buildList(Category.class, 3);
//        factory.addProvider(Category.class, new ListItemProvider<Category>(categories));
//        factory.addProvider(Person.class, new ModelProvider<>(factory, Person.class, new InstanceMold(PersonMold.class)));
//        System.out.println(factory.buildList(Person.class));
//    }
//}
