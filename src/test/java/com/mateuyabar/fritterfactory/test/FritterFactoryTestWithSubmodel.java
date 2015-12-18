package com.mateuyabar.fritterfactory.test;


import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.providers.ListItemProvider;
import com.mateuyabar.fritterfactory.providers.ModelProvider;
import com.mateuyabar.fritterfactory.providers.fritterproviders.FritterProvider;
import com.mateuyabar.fritterfactory.providers.fritterproviders.ModelFritterProvider;
import com.mateuyabar.fritterfactory.providers.primitives.StringProvider;

import org.junit.Test;

import java.util.List;

import javax.inject.Provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FritterFactoryTestWithSubmodel {
    private static final StringProvider stringProvider = new StringProvider();
    public static final String SUBMODEL_NAME = stringProvider.get();
    public static final String MODEL_MOLD_NAME = stringProvider.get();
    public static final String SUBMODEL_MOLD_STATIC_MODEL_NAME = stringProvider.get();



    @Test
    public void testSubModelWithoutMolds(){
        FritterFactory fritterFactory = new FritterFactory();
        SubModel subModel = fritterFactory.build(SubModel.class);
        assertNotNull(subModel);
        assertNotNull(subModel.model);
        assertNotNull(subModel.name);
        assertNotNull(subModel.model.name);
    }

    @Test
    public void testSubModelWithFixedSubmodels(){
        FritterFactory fritterFactory = new FritterFactory();
        List<Model> models = fritterFactory.buildList(Model.class, 2);
        Provider<Model> modelProvider = new ListItemProvider<>(models);
        fritterFactory.addProvider(Model.class, modelProvider);
        List<SubModel> subModels = fritterFactory.buildList(SubModel.class, 30);
        for(SubModel subModel : subModels){
            assertNotNull(subModel);
            assertNotNull(subModel.model);
            assertNotNull(subModel.name);
            assertNotNull(subModel.model.name);
            assertTrue(models.contains(subModel.model));
        }

    }

    @Test
    public void testSubModelAddingMoldPorviderWithStaticSubmodel(){
        FritterFactory fritterFactory = new FritterFactory();
        fritterFactory.addProvider(SubModel.class, new ModelProvider<>(fritterFactory, SubModel.class, new SubModelMoldSaticModel()));
        SubModel subModel = fritterFactory.build(SubModel.class);

        assertNotNull(subModel);
        assertNotNull(subModel.model);
        assertNotNull(subModel.name);
        assertEquals(subModel.name, SUBMODEL_NAME);
        assertNotNull(subModel.model.name);
        assertEquals(subModel.model.name, SUBMODEL_MOLD_STATIC_MODEL_NAME);
    }

    @Test
    public void testSubModelAddingMoldPorviderWithSubmodelFritterProvider(){
        FritterFactory fritterFactory = new FritterFactory();
        fritterFactory.addProvider(SubModel.class, new ModelProvider<>(fritterFactory, SubModel.class, new SubModelMoldSaticModel()));
        SubModel subModel = fritterFactory.build(SubModel.class);

        assertNotNull(subModel);
        assertNotNull(subModel.model);
        assertNotNull(subModel.name);
        assertEquals(subModel.name, SUBMODEL_NAME);
        assertNotNull(subModel.model.name);
        assertEquals(subModel.model.name, SUBMODEL_MOLD_STATIC_MODEL_NAME);
    }



    public static class Model{
        public Model(){}
        public Model(String name) {
            this.name = name;
        }
        public String name;
    }
    public static class SubModel{
        public String name;
        public Model model;
    }
    public static class ModelMold{
        public String name = MODEL_MOLD_NAME;
    }
    public static class SubModelMoldSaticModel{
        String name = SUBMODEL_NAME;
        Model model = new Model(SUBMODEL_MOLD_STATIC_MODEL_NAME);
    }
    public static class SubModelMold{
        String name = SUBMODEL_NAME;
        FritterProvider<Model> model = new ModelFritterProvider<>(Model.class, ModelMold.class);
    }
}
