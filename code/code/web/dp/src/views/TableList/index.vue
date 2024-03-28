<template>
    <div class="table-list">
        <div class="go-back iconfont icon-fanhui" :title="'go back'" @click="goBack"></div>
        <div class="content">
            <div class="left">
                <div class="title">
                    QS Top 500 in the UK
                </div>
                <div class="table-content">
                    <el-table :data="schoolRankList" height="100%" border style="width: 100%" empty-text="No data available">
                        <el-table-column align="center" prop="index" label="Rank" width="100"></el-table-column>
                        <el-table-column align="center" prop="name" label="School Name"></el-table-column>
                    </el-table>
                </div>
            </div>
            <div class="right">
                <div class="title">
                    Student Information
                </div>
                <div class="filters">
                    <div class="filter-row">
                        <div class="filter-item">
                            <div class="label">Profession: </div>
                            <div class="filter-content">
                                <el-select v-model="profession" clearable placeholder="Please select" @change="professionChange">
                                    <el-option
                                      v-for="item in professions"
                                      :key="item.value"
                                      :label="item.label"
                                      :value="item.value"
                                    >
                                    </el-option>
                                </el-select>
                            </div>
                        </div>
                        <div class="filter-item">
                            <div class="label">Organization: </div>
                            <div class="filter-content">
                              <el-input placeholder="Please enter a keyword" class="institution" v-model="institution" @change="orgChange"></el-input>
                            </div>
                        </div>
                        
                    </div>
                    <div class="filter-row">
                        <div class="filter-item">
                            <div class="label">Score: </div>
                            <div class="filter-content">
                                <el-input class="num-input" v-model="scoreMin" @change="scoreMinChangeHandler"></el-input>
                                ~
                                <el-input class="num-input" v-model="scoreMax" @change="scoreMaxChangeHandler"></el-input>
                            </div>
                        </div>
                        <div class="filter-item">
                            <div class="label">Amount: </div>
                            <div class="filter-content">
                                <el-input class="num-input" v-model="moneyMin" @change="amountMinChangeHandler"></el-input>
                                ~
                                <el-input class="num-input" v-model="moneyMax" @change="amountMaxChangeHandler"></el-input>
                                <span style="margin-left: 5px;">$</span>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="table-content">
                    <el-table :data="studentList" height="100%" border style="width: 100%" :empty-text="emptyText" @selection-change="handleSelectionChange">
                        <el-table-column type="selection" align="center" width="50" v-if="show"></el-table-column>
                        <el-table-column align="center" prop="name" label="Name"></el-table-column>
                        <el-table-column align="center" prop="profession" label="Profession"></el-table-column>
                        <el-table-column align="center" prop="score" label="Score"></el-table-column>
                        <el-table-column align="center" prop="offer" label="Offer"></el-table-column>
                        <el-table-column align="center" prop="org" label="Organization"></el-table-column>
                        <el-table-column align="center" prop="money" label="Amount"></el-table-column>
                    </el-table>
                    <div class="bottom-pagination">
                      <span>Total {{total || 0}}</span>
                      <el-pagination
                        :page-size="pageSize"
                        :current-page.sync="currentPage"
                        layout="prev, pager, next"
                        :total="total"
                        @current-change="currentChange"
                      >
                      </el-pagination>
                    </div>
                    
                </div>
                <div class="operate" v-if="user && user.type === 'admin'">
                    <el-button @click="deleteStudents" size="small">Delete</el-button>
                    <el-button @click="editStudent" size="small">Edit</el-button>
                    <el-button type="primary" size="small" @click="addStudent">Add</el-button>
                </div>
            </div>
        </div>
        <el-dialog
            :title="dialogTitle"
            :visible.sync="dialogVisible"
            width="500px"
            top="5vh"
            :before-close="handleClose">
            <div class="new-student">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="shop-ruleForm">
                    <el-form-item label="Name: " prop="name">
                        <el-input placeholder="Please enter the name" v-model="ruleForm.name"></el-input>
                    </el-form-item>
                    <el-form-item label="Profession: " prop="profession">
                        <el-select v-model="ruleForm.profession" placeholder="please select">
                            <el-option
                                v-for="item in professions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            >
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Score: ">
                        <el-input placeholder="please enter the score" v-model="ruleForm.score" @change="scoreChange"></el-input>
                    </el-form-item>
                    <el-form-item label="Offer：">
                        <el-input placeholder="Please enter the offer" v-model="ruleForm.offer"></el-input>
                    </el-form-item>
                    <el-form-item label="Organization: ">
                        <el-input placeholder="Please enter the organization" v-model="ruleForm.org"></el-input>
                    </el-form-item>
                    <el-form-item label="Amount: ">
                        <el-input placeholder="Please enter the amount" v-model="ruleForm.money" @change="moneyChange"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <div class="btns">
                        <el-button @click="reset('ruleForm')" size="small">reset</el-button>
                        <el-button type="primary" @click="confirm('ruleForm')" size="small">confirm</el-button>
                    </div>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>
<script>
export default {
  name: "TableList",
  data() {
    return {
      schoolRankList: [],
      profession: null,
      professions: [],
      scoreMin: null,
      scoreMax: null,
      degree: null,
      degrees: [],
      institution: null,
      moneyMin: null,
      moneyMax: null,
      show: true,
      studentList: [],
      selectedStudents: [],
      dialogVisible: false,
      ruleForm: {
        name: null,
        profession: null,
        score: null,
        offer: null,
        org: null,
        money: null
      },
      rules: {
        name: [
          { required: true, message: "Please enter the name", trigger: "blur" }
        ],
        profession: [
          {
            required: true,
            message: "Please select the profession",
            trigger: "blur"
          }
        ]
      },
      emptyText: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      operateType: "",
      editItem: null
    };
  },
  computed: {
    user() {
      return this.$store.getters.getUser;
    },
    dialogTitle() {
      let title = "";
      if (this.operateType === "add") {
        title = "Add Student";
      } else if (this.operateType === "edit") {
        title = "Edit Student";
      }
      return title;
    }
  },
  created() {
    this.initDatas();
  },
  mounted() {},
  beforeDestroy() {},
  methods: {
    async initDatas() {
      this.currentPage = 1;
      this.getProfessions();
      this.getSchoolRankList();
      this.getStudentList();
    },
    getProfessions() {
      this.professions = [];
      this.$axios.get(SERVICE_URL + "student/selectList?type=2").then(res => {
        if (res.data.code === 0) {
          if (res.data.data) {
            res.data.data.forEach(name => {
              this.professions.push({
                label: name,
                value: name
              });
            });
          }
        }
      });
    },
    getSchoolRankList() {
      let params = {
        pageNo: 1,
        pageSize: 500,
        param: {
          name: "",
          topN: 500
        }
      };
      this.$axios
        .post(SERVICE_URL + "university/pageList", params)
        .then(res => {
          if (res.data.code === 0) {
            this.schoolRankList = [];
            if (!res.data.data) {
              return;
            }
            res.data.data.dataList.forEach(item => {
              this.schoolRankList.push({
                index: item.ranking,
                name: item.name
              });
            });
          }
        });
    },
    getStudentList() {
      if (!this.user) {
        this.studentList = [];
        this.total = 0;
        this.emptyText = "No Permission, Please login first";
        return;
      }
      this.emptyText = "No data available";
      let params = {
        pageNo: this.currentPage,
        pageSize: this.pageSize,
        param: {
          institution: this.institution,
          specialized: this.profession
        }
      };
      if (this.scoreMax) {
        params.param.maxGrades = parseFloat(this.scoreMax);
      }
      if (this.moneyMax) {
        params.param.maxPrice = parseFloat(this.moneyMax);
      }
      if (this.scoreMin) {
        params.param.minGrades = parseFloat(this.scoreMin);
      }
      if (this.moneyMin) {
        params.param.minPrice = parseFloat(this.moneyMin);
      }
      this.$axios.post(SERVICE_URL + "student/pageList", params).then(res => {
        if (res.data.code === 0) {
          let data = res.data.data;
          if (!res.data.data) {
            this.total = 0;
            this.studentList = [];
            return;
          }
          this.total = data.totalCount || 0;
          this.studentList = [];
          data.dataList.forEach((item, index) => {
            this.studentList.push({
              id: item.id,
              name: item.name,
              offer: item.offer,
              profession: item.specialized,
              score: item.grades,
              org: item.institution,
              money: item.price
            });
          });
          if (this.user.type !== "admin") {
            this.studentList.forEach(item => {
              item.name = this.getName(item.name);
            });
          }
        }
      });
    },
    getName(name) {
      let str = "";
      for (let i = 0; i < name.length; i++) {
        if (i === 0) {
          str += name[i];
        } else {
          str += "*";
        }
      }
      return str;
    },
    goBack() {
      this.$router.go(-1);
    },
    handleChange(value) {
      if (isNaN(parseFloat(value))) {
        this.$message.error("Please enter the number format");
      }
    },
    handleSelectionChange(list) {
      this.selectedStudents = list;
    },
    handleClose() {
      this.dialogVisible = false;
      this.operateType = "";
      this.ruleForm = {
        name: null,
        profession: null,
        score: null,
        offer: null,
        org: null,
        money: null
      };
      this.editItem = null;
    },
    scoreChange(value) {
      if (isNaN(parseFloat(value))) {
        this.$message.error(
          "The format entered is incorrect, please enter the number format!"
        );
        this.ruleForm.score = null;
      }
    },
    moneyChange(value) {
      if (isNaN(parseFloat(value))) {
        this.$message.error(
          "The format entered is incorrect, please enter the number format!"
        );
        this.ruleForm.money = null;
      }
    },
    professionChange() {
      if (!this.user) {
        this.$message.warning("No Permission, Please login first");
        this.profession = null;
        return;
      }
      this.currentPage = 1;
      this.getStudentList();
    },
    orgChange() {
      if (!this.user) {
        this.$message.warning("No Permission, Please login first");
        this.institution = null;
        return;
      }
      this.currentPage = 1;
      this.getStudentList();
    },
    currentChange(page) {
      this.currentPage = page;
      this.getStudentList();
    },
    scoreMinChangeHandler(value) {
      if (!this.user) {
        this.$message.warning("No Permission, Please login first");
        this.scoreMin = null;
        return;
      }
      if (value) {
        if (isNaN(parseFloat(value))) {
          this.$message.error(
            "The format entered is incorrect, please enter the number format!"
          );
          return;
        }
        if (
          this.scoreMax &&
          parseFloat(this.scoreMax) < parseFloat(this.scoreMin)
        ) {
          this.$message.error("The maximum cannot be less than the minimum!");
          this.scoreMin = null;
          return;
        }
      }
      this.currentPage = 1;
      this.getStudentList();
    },
    scoreMaxChangeHandler(value) {
      if (!this.user) {
        this.$message.warning("No Permission, Please login first");
        this.scoreMax = null;
        return;
      }
      if (value) {
        if (isNaN(parseFloat(value))) {
          this.$message.error(
            "The format entered is incorrect, please enter the number format!"
          );
          return;
        }
        if (
          this.scoreMin &&
          parseFloat(this.scoreMax) < parseFloat(this.scoreMin)
        ) {
          this.$message.error("The maximum cannot be less than the minimum!");
          this.scoreMax = null;
          return;
        }
      }
      this.currentPage = 1;
      this.getStudentList();
    },
    amountMinChangeHandler(value) {
      if (!this.user) {
        this.$message.warning("No Permission, Please login first");
        this.moneyMin = null;
        return;
      }
      if (value) {
        if (isNaN(parseFloat(value))) {
          this.$message.error(
            "The format entered is incorrect, please enter the number format!"
          );
          return;
        }
        if (
          this.moneyMax &&
          parseFloat(this.moneyMax) < parseFloat(this.moneyMin)
        ) {
          this.$message.error("The maximum cannot be less than the minimum!");
          this.moneyMin = null;
          return;
        }
      }
      this.currentPage = 1;
      this.getStudentList();
    },
    amountMaxChangeHandler(value) {
      if (!this.user) {
        this.$message.warning("No Permission, Please login first");
        this.moneyMax = null;
        return;
      }
      if (value) {
        if (isNaN(parseFloat(value))) {
          this.$message.error(
            "The format entered is incorrect, please enter the number format!"
          );
          return;
        }
        if (
          this.moneyMin &&
          parseFloat(this.moneyMax) < parseFloat(this.moneyMin)
        ) {
          this.$message.error("The maximum cannot be less than the minimum!");
          this.moneyMax = null;
          return;
        }
      }
      this.currentPage = 1;
      this.getStudentList();
    },
    confirm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.operateType === "add") {
            let params = {
              userId: this.user.userId,
              grades: parseFloat(this.ruleForm.score),
              institution: this.ruleForm.org,
              name: this.ruleForm.name,
              offer: this.ruleForm.offer,
              price: parseFloat(this.ruleForm.money),
              specialized: this.ruleForm.profession
            };
            this.$axios.post(SERVICE_URL + "student/add", params).then(res => {
              if (res.data.code === 0) {
                this.$message.success("Add successfully!");
                this.handleClose();
                this.currentPage = 1;
                this.getStudentList();
              } else {
                this.$message.error("Add failure!");
              }
            });
          } else if (this.operateType === "edit") {
            let params = {
              id: this.editItem.id,
              userId: this.user.userId,
              grades: parseFloat(this.ruleForm.score),
              institution: this.ruleForm.org,
              name: this.ruleForm.name,
              offer: this.ruleForm.offer,
              price: parseFloat(this.ruleForm.money),
              specialized: this.ruleForm.profession
            };
            this.$axios.put(SERVICE_URL + "student/edit", params).then(res => {
              if (res.data.code === 0) {
                this.$message.success("Edit successfully!");
                this.handleClose();
                this.currentPage = 1;
                this.getStudentList();
              } else {
                this.$message.error("Edit failure!");
              }
            });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    reset(formName) {
      if (this.operateType === "edit") {
        this.ruleForm = {
          name: this.editItem.name,
          profession: this.editItem.profession,
          score: this.editItem.score,
          offer: this.editItem.offer,
          org: this.editItem.org,
          money: this.editItem.money
        };
      }
      if (this.operateType === "add") {
        this.ruleForm = {
          name: null,
          profession: null,
          score: null,
          offer: null,
          org: null,
          money: null
        };
      }
      // this.$refs[formName].resetFields();
    },
    editStudent() {
      if (this.selectedStudents.length === 0) {
        this.$message.warning("Please select the student to edit!");
        return;
      }
      if (this.selectedStudents.length > 1) {
        this.$message.warning("Please select a student to edit!");
        return;
      }
      this.editItem = this.selectedStudents[0];
      console.log(this.editItem);
      this.ruleForm = {
        name: this.editItem.name,
        profession: this.editItem.profession,
        score: this.editItem.score,
        offer: this.editItem.offer,
        org: this.editItem.org,
        money: this.editItem.money
      };
      this.operateType = "edit";
      this.dialogVisible = true;
    },
    deleteStudents() {
      if (this.selectedStudents.length === 0) {
        this.$message.warning("Please select the student to delete!");
      } else {
        this.$confirm(
          "This operation will be permanently deleted, Whether to continue?",
          "Tips",
          {
            confirmButtonText: "confirm",
            cancelButtonText: "cancel",
            type: "warning"
          }
        )
          .then(() => {
            let ids = [];
            this.selectedStudents.forEach(item => {
              ids.push(item.id);
            });
            let params = {
              studentIds: ids.join(","),
              userId: this.user.userId
            };
            this.$axios
              .post(SERVICE_URL + "student/delete", params)
              .then(res => {
                if (res.data.code === 0) {
                  this.$message({
                    type: "success",
                    message: "Successfully deleted!"
                  });
                  this.currentPage = 1;
                  this.getStudentList();
                } else {
                  this.$message({
                    type: "error",
                    message: "fail to delete!"
                  });
                }
              });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "undeleted!"
            });
          });
      }
    },
    addStudent() {
      this.operateType = "add";
      this.dialogVisible = true;
    }
  }
};
</script>
<style lang="scss" scoped>
.table-list {
  position: absolute;
  width: 100%;
  height: 100%;
  .go-back {
    position: absolute;
    top: 10px;
    left: 10px;
    cursor: pointer;
    z-index: 1;
  }
  .content {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    width: 100%;
    height: 100%;
    display: flex;
    z-index: 0;
    .left {
      width: 30%;
      height: 100%;
      .title {
        padding-top: 10px;
        height: 40px;
        width: 100%;
        text-align: center;
        font-size: 18px;
        line-height: 40px;
      }
      .table-content {
        width: calc(100% - 20px);
        height: calc(100% - 70px);
        margin: 10px;
      }
    }
    .right {
      width: calc(70% - 1px);
      border-left: 1px solid lightgray;
      height: 100%;
      .title {
        height: 40px;
        padding-top: 10px;
        width: 100%;
        text-align: center;
        font-size: 18px;
        line-height: 40px;
      }
      .filters {
        height: 90px;
        width: 100%;
        .filter-row {
          height: 40px;
          line-height: 40px;
          display: flex;
          //   justify-content: center;
          margin-top: 10px;
          .filter-item {
            display: flex;
            margin-right: 10px;
            width: 285px;
            .label {
              width: 100px;
              text-align: right;
            }
            .filter-content {
              margin-left: 5px;
              display: flex;
              width: 180px;
            }
          }
        }
      }
      .table-content {
        width: calc(100% - 20px);
        height: calc(100% - 205px);
        margin: 10px;
      }
      .bottom-pagination {
        display: flex;
        justify-content: flex-end;
        height: 32px;
        line-height: 32px;
      }
      // .el-pagination {

      // }
      .operate {
        position: absolute;
        top: 10px;
        right: 10px;
      }
    }
  }
  .new-student {
    .btns {
      display: flex;
      justify-content: flex-end;
      margin-top: 20px;
    }
  }
}
</style>
<style lang="scss">
.filter-content {
  .el-select {
    .el-input__inner {
      width: 180px;
    }
  }
  .num-input {
    width: 80px;
    .el-input__inner {
      width: 80px;
    }
  }
  .institution.el-input {
    .el-input__inner {
      width: 180px;
    }
  }
  .el-input__inner {
    height: 30px;
    font-size: 12px;
  }
}
.shop-ruleForm {
  .el-input__inner {
    width: 300px;
  }
}
</style>

