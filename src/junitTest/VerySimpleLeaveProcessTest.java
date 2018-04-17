package junitTest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import static org.junit.Assert.*;

public class VerySimpleLeaveProcessTest {

    @Test
    public void testStartProcess() throws Exception {

        // �����������棬ʹ���ڴ����ݿ�
        ProcessEngine processEngine  = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();

        // �������̶����ļ�
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("me/kafeitu/activiti/helloworld/sayhelloleave.bpmn.xml").deploy();

        // ��֤�Ѳ������̶���
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        assertEquals("leavesayhello", processDefinition.getKey());

        // �������̲���������ʵ��
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leavesayhello");
        assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() + ", pdid=" + processInstance.getProcessDefinitionId());
    }
}
