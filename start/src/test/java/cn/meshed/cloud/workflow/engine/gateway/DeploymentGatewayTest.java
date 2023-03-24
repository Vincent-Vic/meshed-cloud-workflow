package cn.meshed.cloud.workflow.engine.gateway;

import cn.meshed.cloud.workflow.ProviderApplication;
import cn.meshed.cloud.workflow.domain.engine.CreateDeployment;
import cn.meshed.cloud.workflow.domain.engine.gateway.DeploymentGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@SpringBootTest(classes = ProviderApplication.class)
@RunWith(SpringRunner.class)
public class DeploymentGatewayTest {

    @Autowired
    private DeploymentGateway deploymentGateway;

    @Test
    public void deployment() {
        CreateDeployment createDeployment = new CreateDeployment();
        createDeployment.setName("xxx");
        createDeployment.setKey("Test");
        createDeployment.setCategory("xxx");
        createDeployment.setXml("<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"             xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"             xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"             xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"             xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\"             xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\"             xmlns:flowable=\"http://flowable.org/bpmn\"             typeLanguage=\"http://www.w3.org/2001/XMLSchema\"             expressionLanguage=\"http://www.w3.org/1999/XPath\"             targetNamespace=\"http://www.flowable.org/processdef\">    <process id=\"oa_leave_1\" name=\"OA 请假\" isExecutable=\"true\">        <startEvent id=\"startEvent\"/>        <sequenceFlow sourceRef=\"startEvent\" targetRef=\"approveTask\"/>        <userTask id=\"approveTask\" name=\"Approve or reject request\"/>        <sequenceFlow sourceRef=\"approveTask\" targetRef=\"decision\"/>        <exclusiveGateway id=\"decision\"/>        <sequenceFlow sourceRef=\"decision\" targetRef=\"externalSystemCall\">            <conditionExpression xsi:type=\"tFormalExpression\">                <![CDATA[          ${approved}        ]]>            </conditionExpression>        </sequenceFlow>        <sequenceFlow  sourceRef=\"decision\" targetRef=\"sendRejectionMail\">            <conditionExpression xsi:type=\"tFormalExpression\">                <![CDATA[          ${!approved}        ]]>            </conditionExpression>        </sequenceFlow>        <serviceTask id=\"externalSystemCall\" name=\"Enter holidays in external system\"                     flowable:class=\"org.flowable.CallExternalSystemDelegate\"/>        <sequenceFlow sourceRef=\"externalSystemCall\" targetRef=\"holidayApprovedTask\"/>        <userTask id=\"holidayApprovedTask\" name=\"Holiday approved\"/>        <sequenceFlow sourceRef=\"holidayApprovedTask\" targetRef=\"approveEnd\"/>        <serviceTask id=\"sendRejectionMail\" name=\"Send out rejection email\"                     flowable:class=\"org.flowable.SendRejectionMail\"/>        <sequenceFlow sourceRef=\"sendRejectionMail\" targetRef=\"rejectEnd\"/>        <endEvent id=\"approveEnd\"/>        <endEvent id=\"rejectEnd\"/>    </process>    <bpmndi:BPMNDiagram id=\"BPMNDiagram_transactional-collapsed-subprocess\">        <bpmndi:BPMNPlane bpmnElement=\"transactional-collapsed-subprocess\" id=\"BPMNPlane_transactional-collapsed-subprocess\">            <bpmndi:BPMNShape bpmnElement=\"startEvent\" id=\"BPMNShape_startEvent\">                <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"100.0\" y=\"163.0\"></omgdc:Bounds>            </bpmndi:BPMNShape>            <bpmndi:BPMNShape bpmnElement=\"sid-D1AA9503-1CA6-4F7C-BE57-C3FB55F87443\" id=\"BPMNShape_sid-D1AA9503-1CA6-4F7C-BE57-C3FB55F87443\">                <omgdc:Bounds height=\"28.0\" width=\"28.0\" x=\"765.0\" y=\"162.0\"></omgdc:Bounds>            </bpmndi:BPMNShape>            <bpmndi:BPMNShape bpmnElement=\"transactionalCollapsedSubprocess\" id=\"BPMNShape_transactionalCollapsedSubprocess\" isExpanded=\"false\">                <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"525.0\" y=\"136.0\"></omgdc:Bounds>            </bpmndi:BPMNShape>            <bpmndi:BPMNShape bpmnElement=\"transactionalSubprocess\" id=\"BPMNShape_transactionalSubprocess\">                <omgdc:Bounds height=\"160.0\" width=\"200.0\" x=\"195.0\" y=\"98.0\"></omgdc:Bounds>            </bpmndi:BPMNShape>            <bpmndi:BPMNEdge bpmnElement=\"sid-ACFA6668-EA33-4776-8C6B-A719753B834C\" id=\"BPMNEdge_sid-ACFA6668-EA33-4776-8C6B-A719753B834C\">                <omgdi:waypoint x=\"129.94999943586103\" y=\"178.0\"></omgdi:waypoint>                <omgdi:waypoint x=\"194.9999999999357\" y=\"178.0\"></omgdi:waypoint>            </bpmndi:BPMNEdge>            <bpmndi:BPMNEdge bpmnElement=\"sid-4EBE8673-726D-47D8-B02A-6A63AEA86DD2\" id=\"BPMNEdge_sid-4EBE8673-726D-47D8-B02A-6A63AEA86DD2\">                <omgdi:waypoint x=\"624.9499999999294\" y=\"176.0\"></omgdi:waypoint>                <omgdi:waypoint x=\"765.0\" y=\"176.0\"></omgdi:waypoint>            </bpmndi:BPMNEdge>            <bpmndi:BPMNEdge bpmnElement=\"sid-2849104E-212F-4940-924A-B0C866942BBB\" id=\"BPMNEdge_sid-2849104E-212F-4940-924A-B0C866942BBB\">                <omgdi:waypoint x=\"394.9499999999995\" y=\"177.28571428571428\"></omgdi:waypoint>                <omgdi:waypoint x=\"524.9999999999985\" y=\"176.35678571428573\"></omgdi:waypoint>            </bpmndi:BPMNEdge>        </bpmndi:BPMNPlane>    </bpmndi:BPMNDiagram></definitions>");
        String deployment = deploymentGateway.deployment(createDeployment);
        System.out.println(deployment);
    }

    @Test
    public void destroy() {
    }
}