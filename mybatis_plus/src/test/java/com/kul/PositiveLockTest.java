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
        //这里小李和小王是同时对数据库进行查询
        //所以虽然写的时候有先后,但是执行的时候是同时执行的,拿到的数据也是一样的都是100
        //所以最终以最后一次修改的为准,价格被改为70
        //而我们增加乐观锁以后
        //第一次数据更新后,之后的数据更新都会失败,因为版本号不一致
        //另外发现一个问题
        //需要建立三个wrapper,分别用于不同的update操作,数据库才会更新
        //如果只用一个wrapper,数据库只会更新一次
        //updateById则不会有这个问题
        Product productLi = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productLi.getPrice());
        UpdateWrapper<Product> wrapper1 = new UpdateWrapper<>();
        UpdateWrapper<Product> wrapper2 = new UpdateWrapper<>();
        UpdateWrapper<Product> wrapper3 = new UpdateWrapper<>();
        wrapper1.eq("id",1);
        wrapper2.eq("id",1);
        wrapper3.eq("id",1);

        //2.小王获取商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productWang.getPrice());

        //3.小李修改商品价格+50
        productLi.setPrice(productLi.getPrice()+50);
//        productMapper.updateById(productLi);
        productMapper.update(productLi,wrapper1);


        //4.小王修改商品价格-30
        productWang.setPrice(productWang.getPrice()-30);
//        int result = productMapper.updateById(productWang);
        int result = productMapper.update(productWang,wrapper2);
        if(result == 0){
            //操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice()-30);
//            productMapper.updateById(productNew);
            productMapper.update(productNew,wrapper3);
        }

        //5.老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板获取的商品价格为：" + productBoss.getPrice());
    }
}
