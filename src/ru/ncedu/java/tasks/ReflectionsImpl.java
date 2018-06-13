package ru.ncedu.java.tasks;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Templerock on 22.10.2017.
 */
public class ReflectionsImpl implements Reflections {
    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        Class clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Wrong argument of getFieldValueByName function");
        }
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) {
        Set<String> set = new HashSet<String>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method: methods)
            if(Modifier.isProtected(method.getModifiers()))
                set.add(method.getName());
        return set;
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        Set<Method> set = new HashSet<Method>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method: methods){
            set.add(method);
        }
        if(clazz.getSuperclass() == null){
            return set;
        }   else{
            Set<Method> tmp = getAllImplementedMethodsWithSupers(clazz.getSuperclass());
            for(Method method: tmp){
                set.add(method);
            }
            return set;
        }
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {
        List<Class> list = new LinkedList<Class>();
        Class superclazz = clazz.getSuperclass();
        if(superclazz == null){
            return list;
        }   else{
            list.add(superclazz);
            List<Class> temp = getExtendsHierarchy(superclazz);
            for(Class clazzz: temp){
                list.add(clazzz);
            }
            return list;
        }
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        Set<Class> set = new HashSet<Class>();
        Class[] interfs = clazz.getInterfaces();
        for(Class clazzz: interfs){
            set.add(clazzz);
        }
        return set;
    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        Class[] exspt = method.getExceptionTypes();
        List<Class> list = new LinkedList<Class>();
        for(Class clazzz: exspt){
            list.add(clazzz);
        }
        return list;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        Class<?> clazz;
        try {
            clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
            clazz = clazz.getClasses()[0];
            Constructor<?> constructor = clazz.getDeclaredConstructor(new Class<?>[0]);
            constructor.setAccessible(true);
            Object secretClassInstance = constructor.newInstance(new Object[0]);
            Method method = clazz.getDeclaredMethod("foo", new Class<?>[0]);
            method.setAccessible(true);
            String result = (String) method.invoke(secretClassInstance, new Object[0]);
            return result;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class was not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor was not found", e);
        } catch (SecurityException e) {
            throw new IllegalStateException("Method is private", e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Constructor error", e);
        }
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        Class<?> clazz;
        try {
            clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
            clazz = clazz.getClasses()[0];
            Constructor<?> constructor = clazz.getDeclaredConstructor(new Class<?>[] {String.class});
            constructor.setAccessible(true);
            Object secretClassInstance = constructor.newInstance(new Object[] {constructorParameter});
            Method method = clazz.getDeclaredMethod("foo", new Class<?>[] {String.class, Integer[].class});
            method.setAccessible(true);
            String result = (String) method.invoke(secretClassInstance, new Object[] {string, integers});
            return result;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class was not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor was not found", e);
        } catch (SecurityException e) {
            throw new IllegalStateException("Method is private", e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Constructor error", e);
        }
    }
}
