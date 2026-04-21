package com.gurpreet.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gurpreet.model.Member;

class MemberTest {

	Member member = new Member("Gurpreet", 21, "Active");
    @Test
    void testMemberAllFields() {
 
        assertAll(
            () -> assertEquals("Gurpreet", member.getName()),
            () -> assertEquals(21, member.getAge()),
            () -> assertEquals("Active", member.getStatus())
        );
    }
}