<template>
  <div class="register">
    <el-dialog
      :title="title"
      :visible="visible"
      width="500px"
      @close="close"
    >
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="160px" class="register-ruleForm">
        <!-- <el-form-item label="Name: " prop="name">
          <el-input placeholder="Please enter your name" v-model="ruleForm.name"></el-input>
        </el-form-item> -->
        <el-form-item label="Email: " prop="email">
          <el-input placeholder="Please enter email" v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password: " prop="password">
          <el-input placeholder="Please enter password" show-password v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="Confirm Password: " prop="rpassword">
          <el-input placeholder="Please enter your password again" show-password v-model="ruleForm.rpassword"></el-input>
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
    var checkEmail = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("Please enter email"));
      }
      setTimeout(() => {
        let emailRegExp = new RegExp(
          "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"
        );
        let isEmail = emailRegExp.test(this.ruleForm.email);
        if (!isEmail) {
          callback(new Error("The email format is incorrect"));
        } else {
          callback();
        }
      }, 1000);
    };
    var CheckRPass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Please enter your password again"));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("The two passwords are inconsistent"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        // name: null,
        email: "",
        password: "",
        rpassword: ""
      },
      rules: {
        // name: [
        //   { required: true, message: "Please enter your name", trigger: "blur" }
        // ],
        email: [
          { required: true, message: "Please enter email", trigger: "blur" },
          // { validator: checkEmail, trigger: "blur" }
          {
            type: "email",
            message: "Please enter the correct email address",
            trigger: ["blur", "change"]
          }
        ],
        password: [
          { required: true, message: "Please enter password", trigger: "blur" }
        ],
        rpassword: [
          {
            required: true,
            message: "Please enter your password again",
            trigger: "blur"
          },
          { validator: CheckRPass, trigger: "blur" }
        ]
      }
    };
  },
  mounted() {},
  methods: {
    confirm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.ruleForm.email === "yawen.yin.xue@gmail.com") {
            this.$message.error("The email has been registered!");
          } else {
            this.$axios.post(SERVICE_URL + "sys/register", {
              password: this.ruleForm.password,
              username: this.ruleForm.email
            }).then(res => {
              if (res.data.code === 0) {
                this.$message.success("Registered successfully");
                this.close()
              } else if (res.data.code === 1) {
                this.$message.error("The email has been registered!");
              } else {
                this.$message.error('error');
              }
            })
          }
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
.register {
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
.register-ruleForm {
  .el-input {
    width: 260px;
  }
}
</style>

