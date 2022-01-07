using Interest;

Console.WriteLine("Please input the principal investment amount:");
double p = 0;
double.TryParse(Console.ReadLine(), out p);

Console.WriteLine("Please input the annual interest rate:");
double r = 0;
double.TryParse(Console.ReadLine(), out r);

Console.WriteLine("Please input the number of times that interest is compounded per year:");
int n = 0;
int.TryParse(Console.ReadLine(), out n);

Console.WriteLine("Please input the number of years the money is invested or borrowed for:");
int t = 0;
int.TryParse(Console.ReadLine(), out t);

Calculate cal = new Calculate
{
    P = p,
    R = r / 100,
    N = n,
    T = t
};

Console.WriteLine("The future value of the investment/loan is: " + cal.GetResult());