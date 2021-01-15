package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;

/**
 * Created by Administrator on 9/1/2016.
 */
public interface QuestionBlockServiceLocal {

    void insertBlock(Question_BlocksWithBLOBs questionBlock);

    Long checkMaxIdBlock();
}
