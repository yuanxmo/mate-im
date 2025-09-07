package com.mate.im.base.statemachine;

/**
 * 状态机接口
 *
 * @author yuanxmo
 */
public interface StateMachine<STATE, EVENT> {

    /**
     * 状态机转移
     *
     * @param state
     * @param event
     * @return
     */
    STATE transition(STATE state, EVENT event);
}
