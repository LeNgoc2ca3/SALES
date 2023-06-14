package com.ngoclbph26472.assignment.service.ipml;

import com.ngoclbph26472.assignment.dto.Cart;
import com.ngoclbph26472.assignment.entity.Products;
import com.ngoclbph26472.assignment.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartIpml implements CartService {

    private final Cart cart = new Cart();
    private Map<Long, Products> dsSanPham;

    @Override
    public void themSanPham(Long id, Products products){
        dsSanPham = cart.getDsGioHang();
        if(dsSanPham.containsKey(id)){
            //nếu mà trùng thì tăng
            Integer soLuongHienTai = dsSanPham.get(id).getQuantity();
            //B2:Cộng dồn
            Integer soLuongMoi = soLuongHienTai + products.getQuantity();
            products.setQuantity(soLuongMoi);
            //B3:Cập nhật lại danh sách
            dsSanPham.put(id,products);
        } else {
            dsSanPham.put(id,products);
        }
    }

    @Override
    public Map<Long,Products> xemSanPham(){
        return cart.getDsGioHang();
    }

    @Override
    public void delete(Long id) {
        dsSanPham.remove(id);
    }

    @Override
    public void deleteAll() {
        dsSanPham.clear();
    }

}
