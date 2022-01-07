namespace Interest
{
    public class Calculate
    {
        public double P { get; set; }
        public double R { get; set; }
        public int N { get; set; }
        public int T { get; set; }

        public double GetResult()
        {
            return Math.Round(P * Math.Pow(1 + R / N, N * T), 2);
        }
    }
}