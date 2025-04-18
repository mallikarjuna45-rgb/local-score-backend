package com.cricket.local_score.Service.addressEntity;

import com.cricket.local_score.Entity.AddressEntity;
import com.cricket.local_score.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService implements IaddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String getAddress2(Integer addressId) {
        return addressRepository.findById(addressId)
                .map(AddressEntity::getAddress2)
                .orElse(null);
    }

    @Override
    public List<Map<String, Object>> getAllAddress2() {
        List<AddressEntity> addresses = addressRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (AddressEntity address : addresses) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", address.getId());
            map.put("address2", address.getAddress2());
            result.add(map);
        }

        return result;
    }
}
