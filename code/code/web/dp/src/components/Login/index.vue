<template>
  <div class="login">
    <el-dialog
      :title="title"
      :visible="visible"
      width="500px"
      @close="close"
    >
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="160px" class="login-ruleForm">
        <el-form-item label="Email：" prop="email">
          <el-input placeholder="Please enter email" v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password: " prop="password">
          <el-input placeholder="Please enter password" show-password v-model="ruleForm.password"></el-input>
        </el-form-item>
        
        <el-form-item>
          <div class="btns">
            <el-button @click="reset('ruleForm')" size="small">reset</el-button>
            <el-button type="primary" size="small" @click="confirm('ruleForm')">confirm</el-button>
          </div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "Login",
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ""
    }
  },
  data() {
    var validateEmail = (rule, value, callback) => {
      if (value) {
        let emailRegExp = new RegExp(
          "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"
        );
        let isEmail = emailRegExp.test(this.ruleForm.email);
        if (isEmail) {
          callback();
        } else {
          callback(new Error("输入邮箱错误"));
        }
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        email: "",
        password: ""
      },
      rules: {
        email: [
          { required: true, message: "Please enter email", trigger: "blur" },
          {
            type: "email",
            message: "Please enter the correct email address",
            trigger: ["blur", "change"]
          }
        ],
        password: [
          { required: true, message: "Please enter password", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {},
  methods: {
    confirm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
            .post(SERVICE_URL + "sys/login", {
              password: this.ruleForm.password,
              username: this.ruleForm.email
            })
            .then(res => {
              if (res.data.code === 0 &&　res.data.data) {
                let data = res.data.data
                let user = {
                  userId: data.userId,
                  type: "",
                  email: this.ruleForm.email,
                  name: data.username
                };
                if (this.ruleForm.email === "yawen.yin.xue@gmail.com") {
                  user.type = "admin";
                }
                sessionStorage.setItem("user", JSON.stringify(user));
                this.$store.commit("setUser", user);
                this.close();
                this.$message.success("Login successful!");
              } else if (res.data.code === 6) {
                this.$message.error('The email or password is incorrect!');
              } else {
                this.$message.error('error');
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    reset(formName) {
      this.$refs[formName].resetFields();
    },
    close() {
      this.$emit("close");
    }
  }
};
</script>
<style lang="scss" scoped>
.login {
  width: 100%;
  height: 100%;
  .btns {
    width: 100%;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
<style lang="scss">
.login-ruleForm {
  .el-input {
    width: 260px;
  }
}
</style>


