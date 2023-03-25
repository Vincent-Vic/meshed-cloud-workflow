package cn.meshed.cloud.workflow.engine.executor.query;

import cn.meshed.cloud.cqrs.QueryExecute;
import cn.meshed.cloud.utils.AssertUtils;
import cn.meshed.cloud.utils.ResultUtils;
import cn.meshed.cloud.workflow.domain.engine.Attachment;
import cn.meshed.cloud.workflow.domain.engine.gateway.TaskGateway;
import cn.meshed.cloud.workflow.engine.data.AttachmentDTO;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class TaskAttachmentQryExe implements QueryExecute<String, SingleResponse<List<AttachmentDTO>>> {

    private final TaskGateway taskGateway;

    /**
     * <h1>查询执行器</h1>
     *
     * @param taskId 任务ID
     * @return {@link SingleResponse<List<AttachmentDTO>>}
     */
    @Override
    public SingleResponse<List<AttachmentDTO>> execute(String taskId) {
        AssertUtils.isTrue(StringUtils.isNotBlank(taskId), "任务ID不能为空");
        List<Attachment> taskAttachments = taskGateway.getTaskAttachments(taskId);
        return ResultUtils.copyList(taskAttachments, AttachmentDTO::new);
    }
}