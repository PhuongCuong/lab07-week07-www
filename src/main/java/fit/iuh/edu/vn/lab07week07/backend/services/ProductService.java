package fit.iuh.edu.vn.lab07week07.backend.services;

import fit.iuh.edu.vn.lab07week07.backend.models.Product;
import fit.iuh.edu.vn.lab07week07.backend.responsitory.ProductResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductResponsitory productResponsitory;

    public Page<Product> findAllpage(int pageNo,int pageSize,String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return productResponsitory.findAll(pageable);
    }

//    public Page<Product> findPaginated(Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<Product> list;
//        List<Product> candidates = productResponsitory.findAll();
//
//        if (candidates.size() < startItem) {
//            list = Collections  .emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, candidates.size());
//            list = candidates.subList(startItem, toIndex);
//        }
//
//        Page<Product> productPage
//                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), candidates.size());
//
//        return productPage;
//    }
}
