package com.cricket.local_score.Service.addressEntity;

import java.util.List;
import java.util.Map;

public interface IaddressService {
    String getAddress2(Integer addressId);
    List<Map<String, Object>> getAllAddress2();
}
