package org.example;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Mobile extends Product{

}
class Product {
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

 class Category {
    // Category class implementation
     public int id;

     public Category() {
     }
 }

public class Main {
    public static void main(String[] args)
            throws NoSuchFieldException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException
    {
            Mobile product = new Mobile();
            Class<? extends Product> productClass = product.getClass();

            Method[] methods= productClass.getMethods();
            for(Method method: methods){
                if (method.getName().startsWith("set") && method.getParameterTypes().length ==1) {
                    Class<?> fieldClass =method.getParameterTypes()[0];
                    method.invoke(product, fieldClass.getConstructor().newInstance());
                }
            }

        if(product.getCategory() != null){
            System.out.println("Reflact Working");
        }else {
            System.out.println("Reflact not Working");
        }
    }
}