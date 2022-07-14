package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Emp;
import generator.service.EmpService;
import generator.mapper.EmpMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【emp】的数据库操作Service实现
* @createDate 2022-06-09 16:15:08
*/
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp>
    implements EmpService{

}




