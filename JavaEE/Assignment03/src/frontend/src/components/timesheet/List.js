import React, { useContext, useState, useEffect, useRef } from "react";
import {
    Row,
    Col,
    Table,
    Input,
    Form,
    message,
    Button,
    Space,
    DatePicker,
    Popconfirm,
} from "antd";
import axios from "axios";
import { PlusSquareOutlined } from "@ant-design/icons";
import "./List.css";
import store from "../../store";
import moment from "moment";
import Utils from "../../common/Utils";

const EditableContext = React.createContext(null);
const EditableRow = ({ index, ...props }) => {
    const [form] = Form.useForm();
    return (
        <Form form={form} component={false}>
            <EditableContext.Provider value={form}>
                <tr {...props} />
            </EditableContext.Provider>
        </Form>
    );
};

const EditableCell = ({
    title,
    editable,
    children,
    dataIndex,
    record,
    handleSave,
    ...restProps
}) => {
    const [editing, setEditing] = useState(false);
    const inputRef = useRef(null);
    const form = useContext(EditableContext);
    useEffect(() => {
        if (editing) {
            inputRef.current.focus();
        }
    }, [editing]);

    const toggleEdit = () => {
        setEditing(!editing);
        form.setFieldsValue({
            [dataIndex]: record[dataIndex],
        });
    };

    const save = async () => {
        try {
            const values = await form.validateFields();
            toggleEdit();
            handleSave(record, values);
        } catch (errInfo) {}
    };

    let childNode = children;

    if (editable && editable(record)) {
        childNode = editing ? (
            <Form.Item
                style={{
                    margin: 0,
                }}
                name={dataIndex}
                rules={[
                    {
                        required: true,
                        message: `Required`,
                    },
                ]}
            >
                <Input ref={inputRef} onPressEnter={save} onBlur={save} />
            </Form.Item>
        ) : (
            <div
                className="editable-cell-value-wrap editable"
                style={{
                    paddingRight: 24,
                }}
                onClick={toggleEdit}
            >
                {children}
            </div>
        );
        return (
            <td {...restProps}>
                {childNode}
            </td>
        );
    }

    return <td {...restProps}>{childNode}</td>;
};

class ContractList extends React.Component {
    constructor(props) {
        super(props);

        this.handleSave = this.handleSave.bind(this);
        this.handleAdd = this.handleAdd.bind(this);
        this.onTableTitle = this.onTableTitle.bind(this);
        this.onDateChange = this.onDateChange.bind(this);

        this.columns = [
            {
                title: "Project",
                dataIndex: "projectId",
                key: "projectId",
                editable: () => true,
                width: "8.3333%",
            },
            {
                title: "WP",
                dataIndex: "workPackageId",
                key: "workPackageId",
                editable: () => true,
                width: "8.3333%",
            },
            {
                title: "Total",
                dataIndex: "total",
                key: "total",
                width: "8.3333%",
                render: (text, d) => {
                    let total = 0;
                    for (let i = 0; i < d.hours.length; ++i) {
                        total += parseFloat(d.hours[i]);
                    }
                    return parseFloat(total).toFixed(2);
                },
            },
            {
                title: "Sun",
                dataIndex: "sun",
                key: "sun",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Mon",
                dataIndex: "mon",
                key: "mon",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Tue",
                dataIndex: "tue",
                key: "tue",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Wed",
                dataIndex: "wed",
                key: "wed",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Thu",
                dataIndex: "thu",
                key: "thu",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Fri",
                dataIndex: "fri",
                key: "fri",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Sat",
                dataIndex: "sat",
                key: "sat",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Notes",
                dataIndex: "notes",
                key: "notes",
                width: "8.3333%",
                editable: () => true,
            },
            {
                title: "Action",
                key: "operation",
                fixed: "right",
                width: "8.3333%",
                render: (text, record) => (
                    <Space>
                        <Popconfirm
                            placement="left"
                            title="Are you sure to delete this row?"
                            onConfirm={() => this.handleDelete(record.id)}
                        >
                            <Button danger size="small">
                                Delete
                            </Button>
                        </Popconfirm>
                    </Space>
                ),
            },
        ];

        this.state = {
            dataSource: [],
            date: new Date(),
        };
    }

    handleDelete = (id) => {
        this.setLoading(true);

        let self = this;
        axios
            .delete("/api/timesheet/token/" + Utils.getToken() + "/id/" + id)
            .then(function (res) {
                self.setLoading(false);
                if (1 === res.data.code) {
                    self.props.history.push("/login");
                } else if (0 === res.data.code) {
                    const dataSource = [...self.state.dataSource];

                    for (let n = 0; n < dataSource.length; ++n) {
                        if (id == dataSource[n].id) {
                            dataSource.splice(n, 1);
                            break;
                        }
                    }

                    self.setState({
                        dataSource: dataSource,
                    });
                } else {
                    message.error(res.data.message);
                }
            })
            .catch(function (err) {
                self.setLoading(false);
                message.error(err.message);
            });
    };

    handleAdd = (record) => {
        this.setLoading(true);

        let self = this;
        axios
            .put(
                "/api/timesheet/token/" +
                    Utils.getToken() +
                    "/date/" +
                    Utils.dateFtt("yyyy-MM-dd", this.state.date)
            )
            .then(function (res) {
                self.setLoading(false);
                if (1 === res.data.code) {
                    self.props.history.push("/login");
                } else if (0 === res.data.code) {
                    self.loadData(self.state.date);
                } else {
                    message.error(res.data.message);
                }
            })
            .catch(function (err) {
                self.setLoading(false);
                message.error(err.message);
            });
    };

    handleSave = (record, values) => {
        let url = "/api/timesheet/";
        let keys = Object.keys(values);
        for (let index in keys) {
            let key = keys[index];
            record[key] = values[key];
            if (key === "sun") {record.hours[0] = values[key];}
            else if (key === "mon") {record.hours[1] = values[key];}
            else if (key === "tue") {record.hours[2] = values[key];}
            else if (key === "wed") {record.hours[3] = values[key];}
            else if (key === "thu") {record.hours[4] = values[key];}
            else if (key === "fri") {record.hours[5] = values[key];}
            else if (key === "sat") {record.hours[6] = values[key];}
        }

        this.setLoading(true);

        let dataSource = this.state.dataSource;

        for (let n = 0; n < dataSource.length; ++n) {
            if (record.id == dataSource[n].id) {
                dataSource[n] = record;
                break;
            }
        }

        let self = this;
        axios
            .post("/api/timesheet/token/" + Utils.getToken(), record)
            .then(function (res) {
                self.setLoading(false);
                if (1 === res.data.code) {
                    self.props.history.push("/login");
                } else if (0 === res.data.code) {
                    self.setState({
                        dataSource: dataSource,
                    });
                } else {
                    message.error(res.data.message);
                }
            })
            .catch(function (err) {
                self.setLoading(false);
                message.error(err.message);
            });
    };

    render() {
        const components = {
            body: {
                row: EditableRow,
                cell: EditableCell,
            },
        };
        const columns = this.columns.map((col) => {
            if (!col.editable) {
                return col;
            }

            return {
                ...col,
                onCell: (record) => ({
                    record,
                    editable: col.editable,
                    dataIndex: col.dataIndex,
                    title: col.title,
                    handleSave: this.handleSave,
                }),
            };
        });

        return (
            <Table
                components={components}
                expandable={{
                    expandIconColumnIndex: 1,
                    defaultExpandAllRows: true,
                }}
                rowClassName={() => "editable-row"}
                dataSource={this.state.dataSource}
                bordered
                columns={columns}
                pagination={{
                    position: ["none"],
                    pageSize: 10000,
                }}
                title={this.onTableTitle}
                rowKey={(record) => record.id}
            />
        );
    }

    setLoading(bLoading) {
        let action = {
            type: "setLoading",
            value: bLoading,
        };

        store.dispatch(action);
    }

    extractData(data) {
        for (let i = 0; i < data.length; ++i) {
            data[i]["sun"] = data[i].hours[0];
            data[i]["mon"] = data[i].hours[1];
            data[i]["tue"] = data[i].hours[2];
            data[i]["wed"] = data[i].hours[3];
            data[i]["thu"] = data[i].hours[4];
            data[i]["fri"] = data[i].hours[5];
            data[i]["sat"] = data[i].hours[6];
        }
        return data;
    }

    loadData(date) {
        this.setLoading(true);

        let self = this;
        axios
            .get(
                "/api/timesheet/token/" +
                    Utils.getToken() +
                    "/date/" +
                    Utils.dateFtt("yyyy-MM-dd", this.state.date)
            )
            .then(function (res) {
                self.setLoading(false);
                if (1 === res.data.code) {
                    self.props.history.push("/login");
                } else if (0 === res.data.code) {
                    let newData = self.extractData(res.data.data);
                    self.setState({
                        dataSource: newData,
                    });
                } else {
                    message.error(res.data.message);
                }
            })
            .catch(function (err) {
                self.setLoading(false);
                message.error(err.message);
            });
    }

    componentDidMount() {
        window.document.title = "My Timesheet";

        this.loadData(this.state.date);

        store.dispatch({
            type: "setMenuItem",
            value: ["/main/timesheet"],
        });
    }

    onDateChange(date) {
        if (!date) {
            return;
        }
        date = date.toDate();
        this.setState({
            date: date,
        });
        this.loadData(date);
    }

    onTableTitle() {
        return (
            <Row>
                <Col span="18">
                    <Space>
                        <Popconfirm
                            placement="right"
                            title="Are you sure to create a new row?"
                            onConfirm={() => this.handleAdd()}
                        >
                            <Button
                                type="primary"
                                icon={<PlusSquareOutlined />}
                                //onClick={this.onAddSupplier}
                            >
                                New Row
                            </Button>
                        </Popconfirm>
                    </Space>
                </Col>
                <Col span="6" style={{ textAlign: "right" }}>
                    <DatePicker
                        onChange={this.onDateChange}
                        picker="date"
                        defaultValue={moment(this.state.date, "YYYY-MM-DD")}
                        format={"YYYY-MM-DD"}
                    />
                </Col>
            </Row>
        );
    }
}

export default ContractList;
