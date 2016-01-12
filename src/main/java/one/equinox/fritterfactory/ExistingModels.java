//package com.mateuyabar.fritterfactory;
//
//import ListedMap;
//import RandomFactory;
//
//import java.util.List;
//
//import javax.inject.Provider;
//
//
//public class ExistingModels {
//    ListedMap<Class<?>, Object> generatedModels;
//
//    public void add(Object model){
//        generatedModels.add(model.getClass(), model);
//    }
//
//    public void addAll(List<Object> models){
//        generatedModels.addAll(models.get(0).getClass(), models);
//    }
//
//    public <T> Provider<T> getProvider(Class<T> clazz){
//        return new ExistingModelsProvider(clazz);
//    }
//
//    private class ExistingModelsProvider<T> implements Provider<T>{
//        Class<T> clazz;
//
//        public ExistingModelsProvider(Class<T> clazz) {
//            this.clazz = clazz;
//        }
//
//        @Override
//        public T get() {
//            List<T> existing = (List<T>) generatedModels.get(clazz);
//            int position =  new RandomFactory().get().nextInt(existing.size());
//            return existing.get(position);
//        }
//    }
//}
