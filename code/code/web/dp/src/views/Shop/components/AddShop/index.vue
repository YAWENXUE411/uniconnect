<template>
    <div class="add-shop">
        <div class="title">add a new store</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="140px" class="add-shop-ruleForm">
                <el-form-item label="Store Name: " prop="name">
                    <el-input placeholder="Please enter the store name" v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="Longitude: " prop="lng">
                    <el-input placeholder="Please enter the longitude" v-model="ruleForm.lng" @change="lnglatChange"></el-input>
                </el-form-item>
                <el-form-item label="Latitude" prop="lat">
                    <el-input placeholder="Please enter the latitude" v-model="ruleForm.lat" @change="lnglatChange"></el-input>
                </el-form-item>
                <el-form-item label="Store Description">
                    <el-input
                        resize="none"
                        type="textarea"
                        placeholder="Please enter the store description"
                        v-model="ruleForm.discript">
                    </el-input>
                </el-form-item>
                <el-form-item>
                    <div class="btns">
                        <el-button @click="cancel('ruleForm')" size="small">Cancel</el-button>
                        <el-button type="primary" @click="confirm('ruleForm')" size="small">confirm</el-button>
                    </div>
                </el-form-item>
            </el-form>
    </div>
</template>
<script>
export default {
  name: "AddShop",
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    shop: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser
    }
  },
  watch: {
    shop: {
      handler(data) {
        this.ruleForm = {
          name: data.name,
          lng: data.lng,
          lat: data.lat,
          discript: data.discript
        };
      },
      immediate: true,
      deep: true
    }
  },
  data() {
    var checkNumber = (rule, value, callback) => {
      if (isNaN(value)) {
        callback(new Error("Please enter the number!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        name: null,
        lng: null,
        lat: null,
        discript: null
      },
      rules: {
        name: [
          {
            required: true,
            message: "Please enter the store name",
            trigger: "blur"
          }
        ],
        lng: [
          {
            required: true,
            message: "Please enter the longitude",
            trigger: "blur"
          },
          { validator: checkNumber, trigger: "blur" }
        ],
        lat: [
          { required: true, message: "Please enter latitude", trigger: "blur" },
          { validator: checkNumber, trigger: "blur" }
        ]
      }
    };
  },
  mounted() {},
  methods: {
    confirm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          let params = {
            desc: this.ruleForm.discript,
            lat: this.ruleForm.lat,
            lng: this.ruleForm.lng,
            name: this.ruleForm.name,
            userId: this.user.userId
          };
          this.$axios.post(SERVICE_URL + "shop/add", params).then(res => {
            if (res.data.code === 0) {
              this.$message.success("Add successfully!");
              this.$emit("confirm");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    cancel(formName) {
      this.$refs[formName].resetFields();
      this.$emit("cancel");
    },
    lnglatChange() {
      console.log(this.ruleForm.lng);
      let lng = parseFloat(this.ruleForm.lng);
      let lat = parseFloat(this.ruleForm.lat);
      if (!isNaN(lng) && !isNaN(lat)) {
        this.$emit("lnglat-change", [lng, lat]);
      } else {
        this.$message.error("The format of the input is incorrect!");
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.add-shop {
  position: absolute;
  width: 400px;
  right: 10px;
  bottom: 20px;
  background: #efefeffa;
  padding: 20px;
  .title {
    font-size: 20px;
    margin-bottom: 20px;
  }
  .btns {
    width: 100%;
    display: flex;
    justify-content: flex-end;
  }
}
</style>

