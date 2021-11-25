import React from "react";
import { Row, Form, Col, Input, Button, message, Alert } from "antd";
import axios from "axios";
import Utils from "../../common/Utils";
import "./Login.css";
import store from "../../store";

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.onFinish = this.onFinish.bind(this);
    }

    componentDidMount() {
        window.document.title = "Timesheet";

        this.checkLogin();
    }

    setLoading(bLoading) {
        let action = {
            type: "setLoading",
            value: bLoading,
        };

        store.dispatch(action);
    }

    onFinish(values) {
        this.setLoading(true);

        let self = this;
        axios
            .post("/api/user/login", values)
            .then(function (res) {
                if (0 === res.data.code) {
                    console.log(res.data.data.token);
                    Utils.setToken(
                        res.data.data.token,
                        res.data.data.employee.admin
                    );

                    self.props.history.push("/main");
                } else {
                    message.error(res.data.message);
                }
                self.setLoading(false);
            })
            .catch(function (err) {
                message.error(err.message);
                self.setLoading(false);
            });
    }

    checkLogin() {
        let token = Utils.getToken();
        if (!token) {
            return;
        }

        let self = this;
        axios
            .get("/api/user/info/" + token)
            .then(function (res) {
                if (0 === res.data.code) {
                    self.props.history.push("/main");
                }
            })
            .catch(function (err) {
                message.error(err.message);
            });
    }

    render() {
        return (
            <Row justify="center" align="middle" className="container">
                <Col span={10}>
                    <Form name="basic" onFinish={this.onFinish}>
                        <Form.Item
                            name="userName"
                            rules={[
                                {
                                    required: true,
                                    message: "Please input your username",
                                },
                            ]}
                        >
                            <Input placeholder="Username" size="large" />
                        </Form.Item>

                        <Form.Item
                            name="password"
                            rules={[
                                {
                                    required: true,
                                    message: "Please input your password",
                                },
                            ]}
                        >
                            <Input.Password
                                placeholder="Password"
                                size="large"
                            />
                        </Form.Item>

                        <Form.Item>
                            <Button type="primary" htmlType="submit" block>
                                Login
                            </Button>
                        </Form.Item>

                        <Alert
                            type="info"
                            message="Demo Users"
                            description="admin:admin or cst:cst"
                            showIcon
                        />
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default Login;
