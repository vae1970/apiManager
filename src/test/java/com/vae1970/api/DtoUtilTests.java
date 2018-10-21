package com.vae1970.api;

import com.alibaba.fastjson.JSONObject;
import com.vae1970.api.entity.BaseEntity;
import com.vae1970.api.util.DtoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

@RunWith(JUnit4.class)
public class DtoUtilTests {

    @Test
    public void toDTO() {
        BaseEntity baseEntity = new BaseEntity().setId(1L).setCreatedAt(new Date()).setCreatedBy(2L);
        BaseEntityDTO dto = DtoUtil.toDTO(baseEntity, BaseEntityDTO.class);
        System.out.println(JSONObject.toJSONString(dto));
    }

    @Test
    public void toDTOList() {
        BaseEntity baseEntity1 = new BaseEntity().setId(1L).setCreatedAt(new Date()).setCreatedBy(2L);
        BaseEntity baseEntity2 = new BaseEntity().setId(2L).setCreatedAt(new Date()).setCreatedBy(3L);
        List<BaseEntity> baseEntityList = new ArrayList<>();
        baseEntityList.add(baseEntity1);
        baseEntityList.add(baseEntity2);
        baseEntityList.add(new BaseEntity());
        List<BaseEntityDTO> baseEntityDTOList = DtoUtil.toDTO(baseEntityList,
                entity -> DtoUtil.toDTO(entity, BaseEntityDTO.class));
        System.out.println(JSONObject.toJSONString(baseEntityDTOList));
    }

    @Test
    public void toDTOSet() {
        BaseEntity baseEntity1 = new BaseEntity().setId(1L).setCreatedAt(new Date()).setCreatedBy(2L);
        BaseEntity baseEntity2 = new BaseEntity().setId(2L).setCreatedAt(new Date()).setCreatedBy(3L);
        Set<BaseEntity> baseEntitySet = new HashSet<>();
        baseEntitySet.add(baseEntity1);
        baseEntitySet.add(baseEntity2);
        baseEntitySet.add(new BaseEntity());
        Set<BaseEntityDTO> baseEntityDTOList = DtoUtil.toDTO(baseEntitySet,
                entity -> DtoUtil.toDTO(entity, BaseEntityDTO.class));
        System.out.println(JSONObject.toJSONString(baseEntityDTOList));
    }

    @Test
    public void toDTOListWithDefault() {
        BaseEntity baseEntity1 = new BaseEntity().setId(2L).setCreatedAt(new Date()).setCreatedBy(3L);
        BaseEntity baseEntity2 = new BaseEntity().setId(20L).setCreatedAt(new Date()).setCreatedBy(21L);
        List<BaseEntity> baseEntityList = new ArrayList<>();
        baseEntityList.add(baseEntity1);
        baseEntityList.add(baseEntity2);
        baseEntityList.add(new BaseEntity());
        List<BaseEntityDTO> baseEntityDTOList = DtoUtil.toDTOWithDefault(baseEntityList,
                entity -> DtoUtil.toDTO(entity, BaseEntityDTO.class));
        System.out.println(JSONObject.toJSONString(baseEntityDTOList));
    }

    @Test
    public void initializeDefaultValue () {
        BaseEntityDTO dto = new BaseEntityDTO();
        dto = DtoUtil.initString(dto);
        System.out.println(JSONObject.toJSONString(dto));
    }

    public static class BaseEntityDTO extends BaseEntity {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
