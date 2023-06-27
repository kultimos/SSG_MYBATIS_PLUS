package com.kul;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kul.mapper.ProductMapper;
import com.kul.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class PositiveLockTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void test1() {
        //����С���С����ͬʱ�����ݿ���в�ѯ
        //������Ȼд��ʱ�����Ⱥ�,����ִ�е�ʱ����ͬʱִ�е�,�õ�������Ҳ��һ���Ķ���100
        //�������������һ���޸ĵ�Ϊ׼,�۸񱻸�Ϊ70
        //�����������ֹ����Ժ�
        //��һ�����ݸ��º�,֮������ݸ��¶���ʧ��,��Ϊ�汾�Ų�һ��
        //���ⷢ��һ������
        //��Ҫ��������wrapper,�ֱ����ڲ�ͬ��update����,���ݿ�Ż����
        //���ֻ��һ��wrapper,���ݿ�ֻ�����һ��
        //updateById�򲻻����������
        Product productLi = productMapper.selectById(1);
        System.out.println("С���ȡ����Ʒ�۸�Ϊ��" + productLi.getPrice());
        UpdateWrapper<Product> wrapper1 = new UpdateWrapper<>();
        UpdateWrapper<Product> wrapper2 = new UpdateWrapper<>();
        UpdateWrapper<Product> wrapper3 = new UpdateWrapper<>();
        wrapper1.eq("id",1);
        wrapper2.eq("id",1);
        wrapper3.eq("id",1);

        //2.С����ȡ��Ʒ�۸�
        Product productWang = productMapper.selectById(1);
        System.out.println("С���ȡ����Ʒ�۸�Ϊ��" + productWang.getPrice());

        //3.С���޸���Ʒ�۸�+50
        productLi.setPrice(productLi.getPrice()+50);
//        productMapper.updateById(productLi);
        productMapper.update(productLi,wrapper1);


        //4.С���޸���Ʒ�۸�-30
        productWang.setPrice(productWang.getPrice()-30);
//        int result = productMapper.updateById(productWang);
        int result = productMapper.update(productWang,wrapper2);
        if(result == 0){
            //����ʧ�ܣ�����
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice()-30);
//            productMapper.updateById(productNew);
            productMapper.update(productNew,wrapper3);
        }

        //5.�ϰ��ѯ��Ʒ�۸�
        Product productBoss = productMapper.selectById(1);
        System.out.println("�ϰ��ȡ����Ʒ�۸�Ϊ��" + productBoss.getPrice());
    }
}
