package com.dao.Feedback;

import com.entity.TbFeedbackEntity;

import java.util.List;

public interface IFeedbackDao{

    /**
     * 添加用户反馈
     * @param feedbackEntity
     * @return
     */
    public boolean addItem(TbFeedbackEntity feedbackEntity);

    /**
     * 删除用户反馈
     * @param feedbackEntity
     * @return
     */
    public boolean deleteItem(TbFeedbackEntity feedbackEntity);

    /**
     * 更新用户反馈
     * @param feedbackEntity
     * @return
     */
    public boolean updateItem(TbFeedbackEntity feedbackEntity);

    /**
     * 按照ID查找用户反馈
     * @param id
     * @return
     */
    public TbFeedbackEntity findById(String id);

    /**
     * 根据其他条件查询
     * @param feedbackEntity
     * @return
     */
    public List<TbFeedbackEntity> findByOther(TbFeedbackEntity feedbackEntity);

    /**
     * 查询所有用户反馈
     * @return
     */
    public List<TbFeedbackEntity> find();

    /**
     * 查询表中的记录总数
     * @return
     */
    int findCount();

    /**
     * 分页显示
     * @param begin
     * @param pageSize
     * @return
     */
    List<TbFeedbackEntity> findByPage(int begin, int pageSize);

    int findCountOfUser(String userId);

    List<TbFeedbackEntity> findByUserAndPage(String userId, int begin, int pageSize);
}
