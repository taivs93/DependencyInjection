package manager;

import annotations.Autowired;
import annotations.Component;
import annotations.Qualifier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static final Map<String, Object> beans = new HashMap<>();

    public void reflectionComponent(Class<?> classOfPackage) throws Exception {
        if (classOfPackage.isAnnotationPresent(Component.class)) {
            Object instance = classOfPackage.getDeclaredConstructor().newInstance();
            String beanName = classOfPackage.getSimpleName();
            beans.put(beanName, instance);
            System.out.println("Init instance " + beanName);
            for (Field field : classOfPackage.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    String dependencyName;
                    if (field.isAnnotationPresent(Qualifier.class)) {
                        dependencyName = field.getAnnotation(Qualifier.class).value();
                    } else {
                        dependencyName = field.getName();
                    }
                    Object fieldInstance = beans.get(dependencyName);
                    if (fieldInstance == null) {
                        for (Object obj : beans.values()) {
                            Class<?> impClass = obj.getClass();
                            if (field.getType().isAssignableFrom(impClass)) {
                                Qualifier qualifierAnnotation = impClass.getAnnotation(Qualifier.class);
                                if (qualifierAnnotation != null && qualifierAnnotation.value().equals(dependencyName)) {
                                    fieldInstance = obj;
                                    break;
                                }
                            }
                        }
                    }
                    field.setAccessible(true);
                    field.set(instance, fieldInstance);
                    System.out.println("Injected dependency: " + dependencyName + " into " + classOfPackage.getName());
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }
}