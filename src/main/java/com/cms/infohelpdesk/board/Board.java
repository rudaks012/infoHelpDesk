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
    private Long bbsIdx;

    @Column(name = "bbs_parent_num", nullable = false)
    private Long bbsParentNum;

    @Column(name = "bbs_thread", nullable = false)
    private Long bbsThread;

    @Column(name = "bbs_depth", nullable = false)
    private Long bbsDepth;

    @Column(name = "bbs_ask_type", nullable = false)
    private Integer bbsAskType;

    @Column(name = "bbs_status", nullable = false)
    private Integer bbsStatus;

    @Column(name = "bbs_notice", nullable = false)
    private Integer bbsNotice = 0;

    @Column(name = "bbs_secret", nullable = false)
    private Integer bbsSecret = 0;

    @Column(name = "bbs_cate", nullable = false)
    private Integer bbsCate = 1;

    @Column(name = "bbs_delete", nullable = false)
    private Integer bbsDelete = 1;

    @Column(name = "bbs_hit", nullable = false)
    private Long bbsHit = 0l;

    @Column(name = "bbs_ip", nullable = false, length = 15)
    private String bbsIp = "";

    @Column(name = "bbs_subject", nullable = false, length = 100)
    private String bbsSubject;

    @Column(name = "bbs_delete_yn", nullable = false, length = 5)
    private String bbsDeleteYn = "n";

    @Column(name = "bbs_userid", nullable = false, length = 50)
    private String bbsUserid = "admin";

    @Column(name = "bbs_writer", nullable = false, length = 50)
    private String bbsWriter;

    @Column(name = "bbs_alarm")
    private Integer bbsAlarm;

    @Column(name = "bbs_moddate")
    private LocalDateTime bbsModdate;

    @Column(name = "bbs_deldate")
    private LocalDateTime bbsDeldate;

    @Column(name = "bbs_passwd")
    private String bbsPasswd;

    @Column(name = "manage_code", length = 10)
    private String manageCode;

    @Column(name = "bbs_useremail", length = 50)
    private String bbsUseremail;

    @Column(name = "bbs_shelf", length = 50)
    private String bbsShelf;

    @Column(name = "bbs_name", length = 50)
    private String bbsName;

    @Column(name = "bbs_call", length = 50)
    private String bbsCall;

    @Column(name = "bbs_contents", columnDefinition = "TEXT")
    private String bbsContents;
}
