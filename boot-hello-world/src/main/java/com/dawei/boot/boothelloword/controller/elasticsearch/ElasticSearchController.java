//package com.dawei.boot.boothelloword.controller.elasticsearch;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.dawei.boot.boothelloword.dao.ElasticBookDao;
//import com.dawei.boot.boothelloword.entity.ElasticEntity;
//import com.dawei.boot.boothelloword.pojo.ResultDto;
//import com.dawei.boot.boothelloword.utils.GsonUtil;
//
///**
// * @author by Dawei on 2019/5/5.
// */
//@RestController
//@RequestMapping(value = "/elasticsearch")
//public class ElasticSearchController {
//
//    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);
//
//
//    //@Resource
//    private ElasticBookDao elasticBookDao;
//
//    @PostMapping(value = "/product/book/save")
//    public ResultDto<ElasticEntity> saveBookDocument(ElasticEntity elasticEntity) {
//        logger.info("To save or update book document , bookEntity={}", elasticEntity);
//        ResultDto<ElasticEntity> resultDto = new ResultDto<>();
//        if(elasticEntity == null) {
//            resultDto.setParamError();
//        } else {
//            resultDto.setSuccess();
//            if (!StringUtils.isEmpty(elasticEntity.getId())) {
//                logger.info("To update book document --- ");
//            }
//            ElasticEntity saveReuslt = elasticBookDao.save(elasticEntity);
//            resultDto.setData(saveReuslt);
//            logger.info("Operation result ={}", GsonUtil.toJson(saveReuslt));
//        }
//        return resultDto;
//    }
//
//
//
//    @GetMapping(value = "/product/book/{id}")
//    public ResultDto<ElasticEntity> getBookDocumentById(@PathVariable String id) {
//        logger.info("To get book document , id={}", id);
//        ResultDto<ElasticEntity> resultDto = new ResultDto<>();
//        if(StringUtils.isEmpty(id)) {
//            resultDto.setParamError();
//        } else {
//            resultDto.setSuccess();
//            Optional<ElasticEntity> optionalElasticEntity = elasticBookDao.findById(id);
//            logger.info("Search Result={}", GsonUtil.toJson(optionalElasticEntity));
//            ElasticEntity elasticEntity = optionalElasticEntity.get();
//            resultDto.setData(elasticEntity);
//        }
//        return resultDto;
//    }
//
//    @GetMapping(value = "/product/book/search/{message}")
//    public ResultDto<List<ElasticEntity>> getBookDocumentByMessage(@PathVariable String message) {
//        logger.info("To get book document , message={}", message);
//        ResultDto<List<ElasticEntity>> resultDto = new ResultDto<>();
//        if(StringUtils.isEmpty(message)) {
//            resultDto.setParamError();
//        } else {
//            resultDto.setSuccess();
//            List<ElasticEntity> elasticEntityList = elasticBookDao.getByMessage(message);
//            logger.info("Search Result={}", GsonUtil.toJson(elasticEntityList));
//            resultDto.setData(elasticEntityList);
//        }
//        return resultDto;
//    }
//
//}
