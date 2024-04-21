package com.cms.infohelpdesk.board;

import com.cms.infohelpdesk.common.base.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@NoArgsConstructor
@Getter
public class Board extends BaseEntity {

    private Long bbs_num;

    @Column(name = "bbs_idx", nullable = false)
    private Long bbs_idx;

    @Column(name = "bbs_parent_num", nullable = false)
    private Long bbs_parent_num;

    @Column(name = "bbs_thread", nullable = false)
    private Long bbs_thread;

    @Column(name = "bbs_depth", nullable = false)
    private Long bbs_depth;

    @Column(name = "bbs_ask_type", nullable = false)
    private Integer bbs_ask_type;

    @Column(name = "bbs_status", nullable = false)
    private Integer bbs_status;

    @Column(name = "bbs_notice", nullable = false)
    private Integer bbs_notice = 0;

    @Column(name = "bbs_secret", nullable = false)
    private Integer bbs_secret = 0;

    @Column(name = "bbs_cate", nullable = false)
    private Integer bbs_cate = 1;

    @Column(name = "bbs_delete", nullable = false)
    private Integer bbs_delete = 1;

    @Column(name = "bbs_hit", nullable = false)
    private Long bbs_hit = 0L;

    @Column(name = "bbs_ip", nullable = false, length = 15)
    private String bbs_ip = "";

    @Column(name = "bbs_subject", nullable = false, length = 100)
    private String bbsSubject;

    @Column(name = "bbs_delete_yn", nullable = false, length = 5)
    private String bbs_delete_yn = "N";

    @Column(name = "bbs_userid", nullable = false, length = 50)
    private String bbs_userid = "admin";

    @Column(name = "bbs_writer", nullable = false, length = 50)
    private String bbs_writer;

    @Column(name = "bbs_alarm")
    private Integer bbs_alarm;

    @Column(name = "bbs_moddate")
    private LocalDateTime bbs_moddate;

    @Column(name = "bbs_deldate")
    private LocalDateTime bbs_deldate;

    @Column(name = "bbs_passwd")
    private String bbs_passwd;

    @Column(name = "manage_code", length = 10)
    private String manage_code;

    @Column(name = "bbs_useremail", length = 50)
    private String bbs_useremail;

    @Column(name = "bbs_shelf", length = 50)
    private String bbs_shelf;

    @Column(name = "bbs_name", length = 50)
    private String bbs_name;

    @Column(name = "bbs_call", length = 50)
    private String bbs_call;

    @Column(name = "bbs_contents", columnDefinition = "TEXT")
    private String bbs_contents;
}
