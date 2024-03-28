<template>
    <div class="shop-info">
        <div class="title">
            <div class="name">{{ shopData.name }}</div>
            <div class="iconfont icon-guanbi" @click="close"></div>
        </div>
        <div class="shop-info-content">
            <el-tabs type="border-card" v-model="tabActive">
                <el-tab-pane label="intro" name="intro">
                    <div class="discribe">
                      <span v-if="shopData.discript">{{ shopData.discript }}</span>
                      <span v-else style="color: gray">empty introduction</span>
                    </div>
                    <div class="shop-mark">
                      <span style="margin-right: 5px;line-height: 20px;margin-top: -1px;">score:</span>
                      <el-rate v-model="shopData.score" show-score text-color="#ff9900" score-template="{value}" disabled></el-rate>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="comment" name="comment">
                    <div class="evaluat-list">
                      <div class="evaluate-item" v-for="(item, index) in evaluates" :key="index">
                        <div class="evaluate">{{ index + 1 }}、{{ item.evaluate }}</div>
                        <div class="commentator-msg">
                            <div class="left">
                              <el-rate v-model="item.mark" disabled></el-rate>
                            </div>
                            <div class="right">
                              <div class="commentator">{{ item.commentator }}</div>
                              <div class="upvotes">
                                  <div class="iconfont icon-dianzan1" v-if="item.upvote" @click="upvoteChange(item)"></div>
                                  <div class="iconfont icon-dianzan" v-else @click="upvoteChange(item)"></div>
                                  <div class="upvote-count">{{ item.upvoteCount }}</div>
                              </div>
                            </div>
                        </div>
                      </div>
                      <span v-if="evaluates.length === 0" style="color: gray">
                        No comments yet
                      </span>
                    </div>
                    <div class="evaluate-pagination">
                      <span>Total {{evaluateTotal}} </span>
                      <el-pagination
                        :page-size="pageSize"
                        :current-page.sync="currentPage"
                        layout="prev, pager, next"
                        :total="evaluateTotal"
                        @current-change="currentChange"
                      >
                      </el-pagination>
                    </div>
                    
                    <div class="add-evaluate">
                      <div class="evaluate-content">
                        <el-input
                          resize="none"
                          type="textarea"
                          placeholder="Publish divine commentary"
                          v-model="textarea">
                        </el-input>
                      </div>
                      <div class="operate">
                        <div class="mark">
                          <el-rate v-model="mark"></el-rate>
                        </div>
                        <el-button class="evaluat-btn" size="mini" @click="addEvaluat">submit</el-button>
                      </div>
                      
                    </div>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>
<script>
export default {
  name: "ShopInfo",
  props: {
    shop: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser;
    },
    shopId() {
      return this.shop ? this.shop.shopId : null;
    }
  },
  watch: {
    shopId: {
      handler(id) {
        if (id) {
          this.tabActive = "intro";
          this.evaluates = [];
          this.evaluateTotal = 0;
          this.currentPage = 1;
          this.shopData = JSON.parse(JSON.stringify(this.shop));
          this.mark = 0;
          this.getShopDetail();
          this.getShopEvaluates(this.shop);
        }
      },
      immediate: true
    }
  },
  data() {
    return {
      shopData: {},
      textarea: null,
      evaluates: [],
      mark: 0,
      tabActive: "intro",
      pageSize: 10,
      currentPage: 1,
      evaluateTotal: 0
    };
  },
  methods: {
    close() {
      this.$emit("close");
    },
    upvoteChange(data) {
      if (!this.user) {
        this.$message.error("Please login first!");
        return;
      }
      data.upvote = !data.upvote;
      let params = {
        commentId: data.evaluateId,
        userId: this.user.userId
      };
      this.$axios.post(SERVICE_URL + "comment/like", params).then(res => {
        if (res.data.code === 0) {
          if (res.data && res.data.data) {
            let str = res.data.data;
            if (str.indexOf("取消点赞成功") > -1) {
              data.upvoteCount -= 1;
            } else if (str.indexOf("评论点赞成功") > -1) {
              data.upvoteCount += 1;
            }
          }
        }
      });
    },
    addEvaluat() {
      if (!this.user) {
        this.$message.error("Please login first!");
        return;
      }
      let params = {
        content: this.textarea,
        score: this.mark,
        shopId: this.shop.shopId,
        userId: this.user.userId
      };
      this.$axios.post(SERVICE_URL + "comment/add", params).then(res => {
        if (res.data.code === 0) {
          this.$message.success("Evaluate success!");
          this.textarea = null;
          this.mark = null;
          this.getShopDetail();
          this.getShopEvaluates();
        }
      });
    },
    getShopDetail() {
      if (this.shopId) {
        this.$axios.get(SERVICE_URL + "shop/detail/" + this.shopId).then(res => {
          if (res.data.code === 0) {
            if (res.data.data) {
              let data = res.data.data;
              this.shopData.score = data.score;
              this.shopData.name = data.name;
              this.shopData.discript = data.desc;
            }
          }
        })
      }
    },
    getShopEvaluates() {
      let params = {
        pageNo: this.currentPage,
        pageSize: this.pageSize,
        param: {
          shopId: this.shopData.shopId,
          userId: this.user ? this.user.userId: null
        }
      };
      this.$axios.post(SERVICE_URL + "comment/pageList", params).then(res => {
        if (res.data.code === 0) {
          let evaluates = [];
          if (!res.data.data) {
            return;
          }
          let data = res.data.data;
          this.evaluateTotal = data.totalCount;
          data.dataList.forEach(item => {
            evaluates.push({
              evaluateId: item.id,
              evaluate: item.content,
              commentator: item.userName,
              upvote: item.userLikeFlag,
              mark: item.score,
              upvoteCount: item.likeNum
            });
          });
          this.evaluates = evaluates;
        } else if (res.data.msg.indexOf("评论信息不存在") > -1) {
          this.evaluates = [];
        } else {
          this.$message.error("Failed to query the information of the store!");
        }
      });
    },
    currentChange(page) {
      this.currentPage = page;
      this.getShopEvaluates();
    }
  }
};
</script>
<style lang="scss" scoped>
.shop-info {
  position: absolute;
  top: 30px;
  width: 400px;
  bottom: 10px;
  left: 10px;
  background: white;
  border: 1px solid #dcdfe6;
  .title {
    width: calc(100% - 20px - 2px);
    height: 50px;
    line-height: 50px;
    display: flex;
    justify-content: space-between;
    padding: 0 10px;
    .name {
      font-size: 24px;
      font-weight: bold;
    }
    .icon-guanbi {
      cursor: pointer;
    }
  }
  .shop-info-content {
    width: calc(100% - 2px);
    height: calc(100% - 50px - 2px);
    .el-tabs {
      width: 100%;
      height: 100%;
    }
    .discribe {
      width: 100%;
      height: calc(100% - 30px);
      overflow-y: auto;
    }
    .shop-mark {
      height: 20px;
      width: 100%;
      display: flex;
    }
    .evaluat-list {
      width: 100%;
      height: calc(100% - 140px);
      overflow-y: auto;
      .evaluate-item {
        padding: 10px 0;
        border-bottom: 1px solid #dcdfe6;
        .evaluate {
          font-size: 14px;
          color: #000;
        }
        .commentator-msg {
          display: flex;
          margin-top: 8px;
          width: 100%;
          justify-content: space-between;
          .left {
            width: 140px;
          }
          .right {
            width: calc(100% - 140px);
            display: flex;
            justify-content: flex-end;
            .commentator {
              color: #9195a3;
              font-size: 12px;
            }
            .upvotes {
              display: flex;
              justify-content: flex-end;
              font-size: 12px;
              margin-left: 10px;
              .icon-dianzan {
                cursor: pointer;
              }
              .icon-dianzan1 {
                cursor: pointer;
                //   color: rgb(0, 0, 255);
              }
              .upvote-count {
                margin-left: 2px;
              }
            }
          }
        }
      }
    }
    .evaluate-pagination {
      display: flex;
      justify-content: flex-end;
      line-height: 32px;
    }
    .mark {
      height: 20px;
      width: 100%;
    }
    .add-evaluate {
      height: 102px;
      width: calc(100% - 30px);
      position: absolute;
      bottom: 10px;
      // .evaluate-content {}
      .operate {
        display: flex;
        justify-content: space-between;
        margin-top: 10px;
      }
      // .evaluat-btn {
      //   position: absolute;
      //   right: 20px;
      //   bottom: 20px;
      // }
    }
  }
}
</style>
<style lang="scss">
.shop-info {
  .shop-info-content {
    .el-tabs__content {
      height: calc(100% - 70px) !important;
      .el-tab-pane {
        height: 100%;
        // display: flex;
        // justify-content: space-between;
        // flex-direction: column;
        .add-evaluate {
          .el-textarea {
            .el-textarea__inner {
              height: 70px;
              width: 100%;
            }
          }
        }
      }
    }
  }
}
</style>


