package com.vae1970.api.util;

import com.vae1970.api.entity.BaseEntity;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DO to DTO util
 *
 * @author vae
 */
public class DtoUtil {

    /**
     * convert DO to DTO
     *
     * @param t     Do object(DO extends BaseEntity)
     * @param clazz DTO.class
     * @param <R>   DTO class
     * @param <T>   DO class(need to be converted)
     * @return DTO object
     */
    public static <R, T extends BaseEntity> R toDTO(T t, Class<R> clazz) {
        R r = null;
        if (t != null) {
            try {
                r = clazz.newInstance();
                BeanUtils.copyProperties(t, r);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    /**
     * convert DO List to DTO List
     *
     * @param doList DO List(DO extends BaseEntity)
     * @param mapper function in converting DO to DTO
     * @param <R>    DTO class
     * @param <T>    DO class(need to be converted)
     * @return DTO list
     */
    public static <R, T extends BaseEntity> List<R> toDTO(List<T> doList, Function<T, R> mapper) {
        return Optional.ofNullable(doList).map(
                list -> list.stream().map(mapper).collect(Collectors.toList())
        ).orElse(new ArrayList<>());
    }

    /**
     * convert DO Set to DTO Set
     *
     * @param doSet  DO Set(DO extends BaseEntity)
     * @param mapper function in converting DO to DTO
     * @param <R>    DTO class
     * @param <T>    DO class(need to be converted)
     * @return DTO set
     */
    public static <R, T extends BaseEntity> Set<R> toDTO(Set<T> doSet, Function<T, R> mapper) {
        return Optional.ofNullable(doSet).map(
                set -> set.stream().map(mapper).collect(Collectors.toSet())
        ).orElse(new HashSet<>());
    }

    /**
     * convert DO List to DTO List(with default value)
     *
     * @param doList extends BaseEntity的DO的List
     * @param mapper function in converting DO to DTO
     * @param <R>    DTO class
     * @param <T>    DO class(need to be converted)
     * @return DTO list
     */
    public static <R, T extends BaseEntity> List<R> toDTOWithDefault(List<T> doList, Function<T, R> mapper) {
        return Optional.ofNullable(doList).map(
                list -> list.stream().map(mapper).map(DtoUtil::initString).collect(Collectors.toList())
        ).orElse(new ArrayList<>());
    }

    /**
     * initialize default value for DTO(prevent frontend show null)
     *
     * @param object DTO or DO
     * @param <T>    object class
     */
    public static <T> T initString(T object) {
        if (object == null) {
            return null;
        }
        Field[] fieldArray = object.getClass().getDeclaredFields();
        for (Field field : fieldArray) {
            String type = field.getGenericType().toString();
            String name = field.getName();
            name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());
            try {
                Method m = object.getClass().getMethod("get" + name);
                Object value = m.invoke(object);

                if ("class java.lang.String".equals(type)) {
                    if (value == null) {
                        field.setAccessible(true);
                        field.set(object, field.getType().getConstructor(field.getType()).newInstance(""));
                    }
                }
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return object;
    }

}
