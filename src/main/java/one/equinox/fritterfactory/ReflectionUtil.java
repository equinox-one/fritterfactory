/*
 * Copyright (c) Mateu Yabar Valles (http://mateuyabar.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package one.equinox.fritterfactory;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {
	
	/**
	 * Returns the declared fields including fields form parent classes that are not transient neither synthetic
	 * @param type class type
	 * @return declared fields
	 */
	public static Field[] getStoredFields(Class type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            Field[] innerFields = c.getDeclaredFields();
            for(Field field:innerFields){
            	if(!field.isSynthetic() &&  !isTransient(field)){
            		fields.add(field);
            	}
            }
        }
        return fields.toArray(new Field[]{});
    }

	public static Field getStoredField(Class type, String name) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			Field[] innerFields = c.getDeclaredFields();
			for(Field field:innerFields){
				if(!field.isSynthetic() &&  !isTransient(field) && field.getName().equals(name)){
					return field;
				}
			}
		}
		return null;
	}

	public static boolean isTransient(Field field){
		return Modifier.isTransient(field.getModifiers());
	}
	

	
	public static boolean isNull(Field field, Object model) throws IllegalAccessException {
		field.setAccessible(true);

			Object value = field.get(model);
			if(value==null)
				return true;
			Class<?> fieldClass = field.getType();
			if(int.class.isAssignableFrom(fieldClass)){
				//TODO check this: we define 0 as a null value for int
				int intValue = (Integer) value;
				return intValue==0;
			}
			return false;
			

	}

	public static boolean isInt(Class<?> fieldClass){
		return Integer.class.isAssignableFrom(fieldClass) || int.class.isAssignableFrom(fieldClass);
	}
	
	public static boolean isBoolean(Class<?> fieldClass){
		return Boolean.class.isAssignableFrom(fieldClass) || boolean.class.isAssignableFrom(fieldClass);
	}

    public static void setValue(Object model, String attribute, Object value) throws NoSuchFieldException, IllegalAccessException {
		Field field = model.getClass().getField(attribute);
		field.setAccessible(true);
		field.set(model, value);
    }

	public static <T> T newInstance(Class<T> clazz) throws FritterFactoryException {
		try{
			return clazz.newInstance();
		} catch (Exception e){
			throw new FritterFactoryException(e);
		}
	}
}
